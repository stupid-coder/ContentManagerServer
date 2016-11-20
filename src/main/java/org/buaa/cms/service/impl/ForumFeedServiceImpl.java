package org.buaa.cms.service.impl;

import org.buaa.cms.dao.ForumFeedDao;
import org.buaa.cms.po.ForumFeedPO;
import org.buaa.cms.service.ForumFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stupid-coder on 11/20/16.
 */
@Service("forumFeedService")
public class ForumFeedServiceImpl implements ForumFeedService {

    @Autowired
    ForumFeedDao forumFeedDao;

    @Override
    public List<ForumFeedPO> getFeedModels(int post_id, int page, int page_size)
    {
        return forumFeedDao.getFeedModels(post_id, page, page_size);
    }

    @Override
    public int addFeedModel(ForumFeedPO feed)
    {
        return forumFeedDao.addFeedModel(feed);
    }

    @Override
    public int updateFeedModel(int feed_id, ForumFeedPO feed)
    {
        return forumFeedDao.updateFeedModel(feed_id, feed);
    }

    @Override
    public int deleteFeedModel(int feed_id)
    {
        return forumFeedDao.deleteFeedModel(feed_id);
    }

    @Override
    public int deleteFeedModels(int post_id)
    {
        return forumFeedDao.deleteFeedModels(post_id);
    }

    @Override
    public int getNumOfFeeds(int post_id)
    {
        return forumFeedDao.getNumOfFeeds(post_id);
    }

}
