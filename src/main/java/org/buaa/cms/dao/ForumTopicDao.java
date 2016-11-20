package org.buaa.cms.dao;

import org.buaa.cms.po.ForumTopicPO;

import java.util.List;

/**
 * Created by stupid-coder on 11/19/16.
 */
public interface ForumTopicDao {

    List<ForumTopicPO> getTopicModels();

    ForumTopicPO getTopicModel(int id);

    int addTopicModel(ForumTopicPO topic);

    int deleteTopicModel(int id);

    int updateTopicModel(int id, ForumTopicPO topic);

    ForumTopicPO getTopicById(int id);

}
