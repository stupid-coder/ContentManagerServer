package org.buaa.cms.service;

import org.buaa.cms.po.ContentPO;

import java.util.List;

/**
 * Created by stupid-coder on 6/28/16.
 */
public interface ContentService {

    public List<ContentPO> getContentModels(String type, String status, int size);

    public ContentPO getContentModel(int id);

    public int addContentModel(ContentPO content);

    public int deleteContentModel(int contentId);

    public int updateContentModel(int contentId, String status);

}
