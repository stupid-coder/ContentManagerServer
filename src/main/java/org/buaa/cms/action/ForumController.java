package org.buaa.cms.action;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.cms.po.ForumFeedPO;
import org.buaa.cms.po.ForumPostPO;
import org.buaa.cms.po.ForumTopicPO;
import org.buaa.cms.service.ForumFeedService;
import org.buaa.cms.service.ForumPostService;
import org.buaa.cms.service.ForumTopicService;
import org.buaa.cms.utils.WrapperHttpUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by stupid-coder on 11/19/16.
 */

@RestController
public class ForumController {

    private static Log logger = LogFactory.getLog(ForumController.class);

    @Resource
    ForumTopicService forumTopicService;

    @Resource
    ForumPostService forumPostService;

    @Resource
    ForumFeedService forumFeedService;

    @RequestMapping(value="/forum/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils getForumTopics(HttpServletRequest request,
                                           HttpServletResponse response)
    {
        List<ForumTopicPO> topics = forumTopicService.getTopicModels();
        if ( topics.size() == 0 ) return new WrapperHttpUtils(null,-1,"failure to get topics");
        else {
            JSONObject wrapper_response = new JSONObject();
            wrapper_response.put("topics",topics);
            wrapper_response.put("size",topics.size());
            return new WrapperHttpUtils(wrapper_response);
        }
    }

    @RequestMapping(value="/forum/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils getForumTopic(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @PathVariable("id") int id)
    {
        ForumTopicPO topic  = forumTopicService.getTopicModel(id);
        if ( topic == null || topic.getId() == null )
            return new WrapperHttpUtils(null,-1,String.format("failure to get topic with id:%d",id));
        else return new WrapperHttpUtils(topic);
    }

    @RequestMapping(value="/forum/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils addForumTopic(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestBody ForumTopicPO topic)
    {
        if ( forumTopicService.addTopicModel(topic) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to add topic");
    }

    @RequestMapping(value="/forum/{id}/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils updateForumTopic(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @PathVariable("id") int id,
                                             @RequestBody ForumTopicPO topic)
    {
        logger.info(String.format("updateForumTopic: id - %d\tcontent - %s", id, topic));
        if ( forumTopicService.updateTopicModel(id,topic) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,String.format("failure to update topic id:%d",id));
    }

    @RequestMapping(value="/forum/{id}/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils deleteForumTopic(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @PathVariable("id") int id)
    {
        // delete the post in thie topic
        List<ForumPostPO> posts = forumPostService.getPostModels(id,0,10000);

        for ( ForumPostPO post : posts ) {
            forumPostService.deletePostModel(Integer.parseInt(post.getId()));
        }

        if ( forumTopicService.deleteTopicModel(id) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,String.format("failure to delete topic id:%d",id));
    }

    // Post
    @RequestMapping(value="/forum/{topic_id}/{page}/{page_size}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils getForumPosts(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @PathVariable("topic_id") int topic_id,
                                          @PathVariable("page") int page,
                                          @PathVariable("page_size") int page_size)
    {
        List<ForumPostPO> forumPostPOs = forumPostService.getPostModels(topic_id,page,page_size);
        if ( forumPostPOs.size() == 0 ) return new WrapperHttpUtils(null,-1,String.format("failure to get posts with id:%d - page:%d - page_size: %d",topic_id,page,page_size));
        else {
            JSONObject wrapper_respnose = new JSONObject();
            wrapper_respnose.put("posts",forumPostPOs);
            wrapper_respnose.put("size",forumPostPOs.size());
            if ( page == 0 ) {
                wrapper_respnose.put("total",forumPostService.getNumOfPosts(topic_id));
            }
            return new WrapperHttpUtils(wrapper_respnose);
        }
    }

    @RequestMapping(value="/forum/{topic_id}/{post_id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils getForumPost(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @PathVariable("topic_id") int topic_id,
                                         @PathVariable("post_id") int post_id)
    {
        ForumPostPO post = forumPostService.getPostModel(post_id);
        if ( post == null ) return new WrapperHttpUtils(null,-1,String.format("failure to get the post: %d",post_id));
        else return new WrapperHttpUtils(post);
    }

    @RequestMapping(value="/forum/{topic_id}/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils addForumPost(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @PathVariable("topic_id") int topic_id,
                                         @RequestBody ForumPostPO post)
    {
        post.setTopic_id(Integer.toString(topic_id));
        if ( forumPostService.addPostModel(post) == 1) return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to add post");
    }

    @RequestMapping(value="/forum/{topic_id}/{post_id}/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils updateForumPost(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @PathVariable("topic_id") int topic_id,
                                            @PathVariable("post_id") int post_id,
                                            @RequestBody ForumPostPO post)
    {
        if ( forumPostService.updatePostModel(post_id,post) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to update post");
    }

    @RequestMapping(value="/forum/{topic_id}/{post_id}/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils deleteForumPost(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @PathVariable("topic_id") int topic_id,
                                            @PathVariable("post_id") int post_id)
    {
        // Delete the feed in this post
        forumFeedService.deleteFeedModels(post_id);

        if ( forumPostService.deletePostModel(post_id) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to delete post");
    }

    // feed
    @RequestMapping(value="/forum/{topic_id}/{post_id}/{page}/{page_size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils getForumFeeds(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @PathVariable("topic_id") int topic_id,
                                          @PathVariable("post_id") int post_id,
                                          @PathVariable("page") int page,
                                          @PathVariable("page_size") int page_size)
    {
        List<ForumFeedPO> feedPOs = forumFeedService.getFeedModels(post_id,page,page_size);
        if ( feedPOs == null || feedPOs.size() == 0 ) {
            return new WrapperHttpUtils(null,-1,"failure to get feeds");
        } else {
            JSONObject response_wrapper = new JSONObject();
            response_wrapper.put("feeds",feedPOs);
            response_wrapper.put("size",feedPOs.size());
            if ( page == 0 )
            {
                response_wrapper.put("total",forumFeedService.getNumOfFeeds(post_id));
            }
            return new WrapperHttpUtils(response_wrapper);
        }
    }

    @RequestMapping(value="/forum/{topic_id}/{post_id}/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils addForumFeed(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @PathVariable("topic_id") int topic_id,
                                         @PathVariable("post_id") int post_id,
                                         @RequestBody ForumFeedPO feed)
    {
        feed.setPost_id(Integer.toString(post_id));
        if ( forumFeedService.addFeedModel(feed) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to add forum");
    }

    @RequestMapping(value="/forum/{topic_id}/{post_id}/{feed_id}/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils updateForumFeed(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @PathVariable("topic_id") int topic_id,
                                            @PathVariable("post_id") int post_id,
                                            @PathVariable("feed_id") int feed_id,
                                            @RequestBody ForumFeedPO feed)
    {
        if ( forumFeedService.updateFeedModel(feed_id,feed) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to update feed");
    }


    @RequestMapping(value="/forum/{topic_id}/{post_id}/{feed_id}/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils deleteForumFeed(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @PathVariable("topic_id") int topic_id,
                                            @PathVariable("post_id") int post_id,
                                            @PathVariable("feed_id") int feed_id)
    {
        if ( forumFeedService.deleteFeedModel(feed_id) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to delete the feed");
    }
}
