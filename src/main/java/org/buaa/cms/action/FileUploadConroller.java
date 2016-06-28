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

    private static final String path = System.getenv("CATALINA_HOME")+"/pictures";

    @RequestMapping(value="/uploadtest", method = RequestMethod.GET)
    public String uploadtest()
    {
        return "upload";
    }

    @RequestMapping(value="/upload/", method = RequestMethod.POST)
    @ResponseBody
    public List<String> upload(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("picture") MultipartFile[] files)
    {
        File uploadRootDir = new File(path);

        if ( !uploadRootDir.exists() ) {
            uploadRootDir.mkdir();
        }

        List<String> uris = new ArrayList<String>();

        for ( int i = 0; i < files.length; ++ i )
        {
            MultipartFile file = files[i];
            String name = file.getOriginalFilename();
            if ( name != null && name.length() > 0 ) {
                try {
                    String temp_name = TimeUtils.GetTimeStamps()+"_"+name;
                    File sub_path = new File(uploadRootDir.getAbsolutePath()+File.separator+Math.abs(temp_name.hashCode()%100));

                    if ( !sub_path.exists() ) sub_path.mkdir();

                    byte[] bytes = file.getBytes();

                    File serverFile = new File(sub_path.getAbsolutePath()+File.separator+temp_name);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                    stream.write(bytes);
                    stream.close();

                    uris.add("picture/"+temp_name);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return uris;
    }


}
