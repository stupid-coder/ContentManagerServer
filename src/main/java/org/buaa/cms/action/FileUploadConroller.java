package org.buaa.cms.action;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.cms.utils.TimeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    private static final String path = System.getProperty("catalina.base")+"/cms/pictures/";

    @RequestMapping(value="/uploadtest", method = RequestMethod.GET)
    public String uploadtest()
    {
        return "upload";
    }

    @RequestMapping(value="/upload/{id}/", method = RequestMethod.POST)
    @ResponseBody
    public List<String> upload(HttpServletRequest request, HttpServletResponse response,
                               @PathVariable("id") int id,
                               @RequestParam("picture") MultipartFile[] files)
    {
        File uploadRootDir = new File(path+id);

        if ( !uploadRootDir.exists() ) {
            uploadRootDir.mkdirs();
        }

        List<String> uris = new ArrayList<String>();

        for ( int i = 0; i < files.length; ++ i )
        {
            MultipartFile file = files[i];
            String name = file.getOriginalFilename();
            String temp_name = i+"."+name.substring(name.lastIndexOf(".")+1);

            if ( name != null && name.length() > 0 ) {
                try {

                    byte[] bytes = file.getBytes();

                    File temp_file = new File(uploadRootDir.getAbsolutePath()+File.separator+temp_name);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(temp_file));

                    stream.write(bytes);
                    stream.close();

                    uris.add("pictures"+File.separator+id+File.separator+temp_name);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return uris;
    }


}
