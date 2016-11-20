package org.buaa.cms.service.impl;

import org.buaa.cms.dao.ForumPostDao;
import org.buaa.cms.po.ForumPostPO;
import org.buaa.cms.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stupid-coder on 11/19/16.
 */
@Service("forumPostService")
public class ForumPostServiceImpl implements ForumPostService {
    @Autowired
    ForumPostDao forumPostDao;

    @Override
    public List<ForumPostPO> getPostModels(int topic_id, int page, int page_size)
    {
        return forumPostDao.getPostModels(topic_id,page,page_size);
    }

    @Override
    public ForumPostPO getPostModel(int id) { return forumPostDao.getPostModel(id); }

    @Override
    public int addPostModel(ForumPostPO post) { return forumPostDao.addPostModel(post); }

    @Override
    public int deletePostModel(int id) {
        return forumPostDao.deletePostModel(id);
    }

    @Override
    public int deletePostModels(int topic_id) {
        return forumPostDao.deletePostModels(topic_id);
    }

    @Override
    public int updatePostModel(int id, ForumPostPO post) { return forumPostDao.updatePostModel(id,post); }

    @Override
    public int getNumOfPosts(int topic_id) { return forumPostDao.getNumOfPosts(topic_id); }
}
