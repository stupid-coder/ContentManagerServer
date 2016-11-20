package org.buaa.cms.service.impl;

import org.buaa.cms.dao.ForumTopicDao;
import org.buaa.cms.po.ForumPostPO;
import org.buaa.cms.po.ForumTopicPO;
import org.buaa.cms.service.ForumTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stupid-coder on 11/19/16.
 */

@Service("ForumTopicService")
public class ForumTopicServiceImpl implements ForumTopicService {

    @Autowired
    ForumTopicDao forumTopicDao;

    public List<ForumTopicPO> getTopicModels() {
        return forumTopicDao.getTopicModels();
    }

    public ForumTopicPO getTopicModel(int i) {
        return forumTopicDao.getTopicModel(i);
    }

    public int addTopicModel(ForumTopicPO topic) {
        return forumTopicDao.addTopicModel(topic);
    }

    public int deleteTopicModel(int id) {
        return forumTopicDao.deleteTopicModel(id);
    }

    public int updateTopicModel(int id, ForumTopicPO topic)
    {
        return forumTopicDao.updateTopicModel(id, topic);
    }
}
