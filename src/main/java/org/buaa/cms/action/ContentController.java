package org.buaa.cms.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.cms.po.ContentPO;

import org.buaa.cms.service.ContentService;
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

    @RequestMapping(value="/content/*/{type}/{status}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContentPO> getContent(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @PathVariable("type") String type,
                                      @PathVariable("status") String status)
    {
        return contentService.getContentModel(type,status);
    }

    @RequestMapping(value="/content/{id}/", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ContentPO getContent(HttpServletRequest request,
                                HttpServletResponse response,
                                @PathVariable("id") Integer id)
    {
        return contentService.getContentModel(id);
    }

    @RequestMapping(value="/content/",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addContent(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody ContentPO content)
    {
        contentService.addContentModel(content);
    }

    @RequestMapping(value="/content/{id}/*/{status}/",method = RequestMethod.PUT)
    public void updateContent(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable("id") int id,
                                   @PathVariable("status") String type)
    {
        contentService.updateContentModel(id,type);
    }

    @RequestMapping(value="/content/{contentId}/", method = RequestMethod.DELETE)
    public void deleteContent(HttpServletRequest request,HttpServletResponse response,
                                   @PathVariable("contentId") int id)
    {
        contentService.deleteContentModel(id);
    }
}
