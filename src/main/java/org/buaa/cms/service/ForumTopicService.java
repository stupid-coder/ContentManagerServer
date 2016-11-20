package org.buaa.cms.service;

import org.buaa.cms.po.ForumTopicPO;

import java.util.List;

/**
 * Created by stupid-coder on 11/19/16.
 */
public interface ForumTopicService {

    public List<ForumTopicPO> getTopicModels();

    public ForumTopicPO getTopicModel(int id);

    public int addTopicModel(ForumTopicPO topic);

    public int deleteTopicModel(int id);

    public int updateTopicModel(int id, ForumTopicPO topicPO);

}
