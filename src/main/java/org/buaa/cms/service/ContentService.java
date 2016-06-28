package org.buaa.cms.service;

import org.buaa.cms.po.ContentPO;

import java.util.List;

/**
 * Created by stupid-coder on 6/28/16.
 */
public interface ContentService {

    public List<ContentPO> getContentModel(String type, String status);

    public ContentPO getContentModel(int id);

    public void addContentModel(ContentPO content);

    public void deleteContentModel(int contentId);

    public void updateContentModel(int contentId, String status);

}
