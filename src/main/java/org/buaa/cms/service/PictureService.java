package org.buaa.cms.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by stupid-coder on 7/18/16.
 */
public interface PictureService {

    public String save(MultipartFile file, String rootDir);

}
