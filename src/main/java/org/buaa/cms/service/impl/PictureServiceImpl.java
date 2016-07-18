package org.buaa.cms.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buaa.cms.service.PictureService;
import org.buaa.cms.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by stupid-coder on 7/18/16.
 */
@Service("PictureService")
public class PictureServiceImpl implements PictureService {

    private Log logger = LogFactory.getLog(PictureService.class);

    @Override
    public String save(MultipartFile file, String rootDir) {

        String origin_filename = file.getOriginalFilename();
        String prefix = origin_filename.substring(origin_filename.lastIndexOf(".")+1);

        try {

            byte[] bytes = file.getBytes();
            String md5 = MD5Utils.getMD5(bytes);
            int index = Math.abs(md5.hashCode()%100);

            String save_path = rootDir+File.separator+index;

            File save_path_file = new File(save_path);
            if ( !save_path_file.exists() ) save_path_file.mkdirs();

            String relate_filename = index+File.separator+md5+"."+prefix;

            File temp_file = new File(rootDir+File.separator+relate_filename);

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(temp_file));

            stream.write(bytes);
            stream.close();

            return relate_filename;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
