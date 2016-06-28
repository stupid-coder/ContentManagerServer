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
    public List<ContentPO> getContentModel(String type, String status) {
        return contentDao.getContentModel(type,status);
    }

    @Override
    public ContentPO getContentModel(int id) {
        return contentDao.getContentModel(id);
    }

    @Override
    public void addContentModel(ContentPO content) {
        contentDao.addContentModel(content);
    }

    @Override
    public void deleteContentModel(int id) {
        contentDao.deleteContentModel(id);
    }

    @Override
    public void updateContentModel(int id, String status) {
        contentDao.updateContentModel(id,status);
    }
}
