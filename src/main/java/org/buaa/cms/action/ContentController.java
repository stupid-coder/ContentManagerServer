package org.buaa.cms.action;

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

    @RequestMapping(value="/content/*/{type}/{status}/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WrapperHttpUtils getContents(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @PathVariable("type") String type,
                                        @PathVariable("status") String status,
                                        @PathVariable("size") int size)
    {
        List<ContentPO> contentPOList  = contentService.getContentModels(type,status,size);
        if ( contentPOList.size() == 0 ) return new WrapperHttpUtils(null,-1,"failure to get contents");
        else return new WrapperHttpUtils(contentPOList);
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

    @RequestMapping(value="/content/{id}/*/{status}/",method = RequestMethod.PUT)
    public WrapperHttpUtils updateContent(HttpServletRequest request, HttpServletResponse response,
                                          @PathVariable("id") int id,
                                          @PathVariable("status") String type)
    {
        if ( contentService.updateContentModel(id,type) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to update content");
    }

    @RequestMapping(value="/content/{contentId}/", method = RequestMethod.DELETE)
    public WrapperHttpUtils deleteContent(HttpServletRequest request,HttpServletResponse response,
                                          @PathVariable("contentId") int id)
    {
        if ( contentService.deleteContentModel(id) == 1 )
            return new WrapperHttpUtils(null);
        else return new WrapperHttpUtils(null,-1,"failure to delete content");
    }
}
