package org.buaa.cms.action;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.cms.po.ContentPO;

import org.buaa.cms.service.ContentService;
import org.buaa.cms.utils.WrapperHttpUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by stupid-coder on 6/28/16.
 */

@RestController
public class ContentController {

    private static Log logger = LogFactory.getLog(ContentController.class);

    @Resource
    ContentService contentService;

    @RequestMapping(value="/content/*/{type}/{status}/{size}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils getContents(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @PathVariable("type") String type,
                                        @PathVariable("status") String status,
                                        @PathVariable("size") int size)
    {
        List<ContentPO> contentPOList  = contentService.getContentModels(type,status,size);
        if ( contentPOList.size() == 0 ) return new WrapperHttpUtils(null,-1,"failure to get contents");
        else {
            JSONObject wrapper_response = new JSONObject();
            wrapper_response.put("contents",contentPOList);
            wrapper_response.put("size",contentPOList.size());
            int status0 = 0;
            for ( ContentPO content : contentPOList ) {
                if ( content.getStatus().compareTo("0") == 0 ) status0++;
            }
            wrapper_response.put("status0",status0);
            wrapper_response.put("status1",contentPOList.size()-status0);
            return new WrapperHttpUtils(wrapper_response);
        }
    }

    @RequestMapping(value="/content/{id}/", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils getContent(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @PathVariable("id") Integer id)
    {
        ContentPO contentPO = contentService.getContentModel(id);
        if ( contentPO == null || contentPO.getId() == null )
            return new WrapperHttpUtils(null,-1,"failure to get content");
        else return new WrapperHttpUtils(contentPO);
    }

    @RequestMapping(value="/content/",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils addContent(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody ContentPO content)
    {
        if ( contentService.addContentModel(content) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to add content");
    }

    @RequestMapping(value="/content/{id}/",method = RequestMethod.PUT)
    public WrapperHttpUtils updateContent(HttpServletRequest request, HttpServletResponse response,
                                          @PathVariable("id") int id,
                                          @RequestBody ContentPO content)
    {
        logger.info(String.format("updateContent: id - %d\tcontent - %s",id,content));
        if ( contentService.updateContentModel(id,content) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to update content");
    }

    @RequestMapping(value="/content/{id}/", method = RequestMethod.DELETE)
    public WrapperHttpUtils deleteContent(HttpServletRequest request,HttpServletResponse response,
                                          @PathVariable("id") int id)
    {
        if ( contentService.deleteContentModel(id) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to delete content");
    }

    @RequestMapping(value="/content/rec/{id}/{type}/{status}/{size}/", method = RequestMethod.GET)
    public WrapperHttpUtils recContent(HttpServletRequest request,HttpServletResponse response,
                                       @PathVariable("id") int id,
                                       @PathVariable("type") String type,
                                       @PathVariable("status") String status,
                                       @PathVariable("size") int size)
    {
        List<ContentPO> recContent = contentService.recContentModels(type,status,id,size);
        if ( recContent == null || recContent.size() == 0 )
            return new WrapperHttpUtils(null,-1,"failure to recommendation content");
        return new WrapperHttpUtils(recContent);
    }

    @RequestMapping(value="/content/prev/{id}/{type}/{status}/", method = RequestMethod.GET )
    public WrapperHttpUtils prevContent(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("id") int id,
                                        @PathVariable("type") String type,
                                        @PathVariable("status") String status)
    {
        ContentPO contentPO = contentService.prevnextContentModel(type,status,id,-1);
        if ( contentPO == null ) return new WrapperHttpUtils(null,-1,"failure to get previous content");
        else return new WrapperHttpUtils(contentPO);
    }

    @RequestMapping(value="/content/next/{id}/{type}/{status}/", method = RequestMethod.GET )
    public WrapperHttpUtils nextContent(HttpServletRequest request,HttpServletResponse response,
                                        @PathVariable("id") int id,
                                        @PathVariable("type") String type,
                                        @PathVariable("status") String status)
    {
        ContentPO contentPO = contentService.prevnextContentModel(type,status,id,1);
        if ( contentPO == null ) return new WrapperHttpUtils(null,-1,"failure to get next content");
        else return new WrapperHttpUtils(contentPO);
    }
}
