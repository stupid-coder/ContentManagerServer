package org.buaa.cms.service.impl;

import org.buaa.cms.dao.ContentDao;
import org.buaa.cms.po.ContentPO;
import org.buaa.cms.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stupid-coder on 6/28/16.
 */

@Service("contentService")
public class ContentServiceImpl implements ContentService
{
    @Autowired
    ContentDao contentDao;

    @Override
    public List<ContentPO> getContentModels(String type, String status, int size) {
        return contentDao.getContentModels(type,status,size);
    }

    @Override
    public ContentPO getContentModel(int id) {
        return contentDao.getContentModel(id);
    }

    @Override
    public int addContentModel(ContentPO content) {
        return contentDao.addContentModel(content);
    }

    @Override
    public int deleteContentModel(int id) {
        return contentDao.deleteContentModel(id);
    }

    @Override
    public int updateContentModel(int id, ContentPO content) {
        return contentDao.updateContentModel(id,content);
    }

    @Override
    public List<ContentPO> recContentModels(String type, String status, int id, int size) {
        List<ContentPO> contentPOList = contentDao.recContentModels(type,status,id,size);
        for ( ContentPO contentPO : contentPOList ) {
            if ( Integer.parseInt(contentPO.getId()) == id ) {
                contentPOList.remove(contentPO);
                break;
            }
        }
        return contentPOList;
    }

    @Override
    public ContentPO prevnextContentModel(String type, String status, int id, int flag)
    {
        return contentDao.prevnextContentModel(type,status,id,flag);
    }
}
