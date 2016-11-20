package org.buaa.cms.service;

import org.buaa.cms.po.ForumFeedPO;

import java.util.List;

/**
 * Created by stupid-coder on 11/20/16.
 */
public interface ForumFeedService {

    List<ForumFeedPO> getFeedModels(int post_id, int page, int page_size);

    int addFeedModel(ForumFeedPO feed);

    int updateFeedModel(int feed_id, ForumFeedPO feed);

    int deleteFeedModel(int feed_id);

    int deleteFeedModels(int post_id);

    int getNumOfFeeds(int post_id);
}
