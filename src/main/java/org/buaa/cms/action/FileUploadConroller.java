package org.buaa.cms.action;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.cms.service.PictureService;
import org.buaa.cms.utils.TimeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stupid-coder on 6/28/16.
 */

@Controller
@RequestMapping(value="/picture")
public class FileUploadConroller {

    private static Log logger = LogFactory.getLog(FileUploadConroller.class);

    private static final String path = System.getProperty("catalina.base")+"/cms/picture/";

    @Resource
    PictureService pictureService;

    /*
    @RequestMapping(value="/uploadtest", method = RequestMethod.GET)
    public String uploadtest()
    {
        return "upload";
    }
    */

    @RequestMapping(value="/upload/", method = RequestMethod.POST)
    @ResponseBody
    public List<String> upload(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("picture") MultipartFile[] files)
    {
        List<String> uris = new ArrayList<String>();

        for ( int i = 0; i < files.length; ++ i )
        {
            uris.add(pictureService.save(files[i],path));
        }
        return uris;
    }


}
