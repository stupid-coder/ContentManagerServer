package org.buaa.cms.dao;

import org.buaa.cms.po.ContentPO;

import java.util.List;

/**
 * Created by stupid-coder on 6/28/16.
 */
public interface ContentDao {

    public List<ContentPO> getContentModel(String type, String status);

    public ContentPO getContentModel(int id);

    public void addContentModel(ContentPO content);

    public void deleteContentModel(int id);

    public void updateContentModel(int id, String status);

    public ContentPO getContentById(int id);

}
