package org.buaa.cms.dao;

import org.buaa.cms.po.ContentPO;

import java.util.List;

/**
 * Created by stupid-coder on 6/28/16.
 */
public interface ContentDao {

    public List<ContentPO> getContentModels(String type, String status, int size);

    public ContentPO getContentModel(int id);

    public int addContentModel(ContentPO content);

    public int deleteContentModel(int id);

    public int updateContentModel(int id, ContentPO content);

    public ContentPO getContentById(int id);

    public List<ContentPO> recContentModels(String type, String status, int id, int size);

    public ContentPO prevnextContentModel(String type, String status, int id, int flag);

}
