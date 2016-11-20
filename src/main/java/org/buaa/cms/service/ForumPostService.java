package org.buaa.cms.service;

import org.buaa.cms.po.ForumPostPO;

import java.util.List;

/**
 * Created by stupid-coder on 11/19/16.
 */
public interface ForumPostService {

    List<ForumPostPO> getPostModels(int topic_id, int page, int page_size);

    ForumPostPO getPostModel(int id);

    int addPostModel(ForumPostPO post);

    int deletePostModels(int topic_id);

    int deletePostModel(int id);

    int updatePostModel(int id, ForumPostPO post);

    int getNumOfPosts(int topic_id);
}
