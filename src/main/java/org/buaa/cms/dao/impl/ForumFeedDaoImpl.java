package org.buaa.cms.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.buaa.cms.dao.ForumFeedDao;
import org.buaa.cms.po.ForumFeedPO;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stupid-coder on 11/20/16.
 */
@Repository
public class ForumFeedDaoImpl extends JdbcDaoSupport implements ForumFeedDao {

    static final String table_name = "Feed";

    @Resource
    private DataSource dataSource;

    @PostConstruct
    private void initialize() { setDataSource(dataSource); }

    @Override
    public List<ForumFeedPO> getFeedModels(int post_id, int page, int page_size)
    {
        String sql = String.format("SELECT id,post_id,meta_info,content,create_time,modify_time FROM %s WHERE post_id=? ORDER BY create_time LIMIT %d.%d",table_name,page*page_size,page_size);
        return this.getJdbcTemplate().query(sql,new ForumFeedPO(),post_id);
    }

    @Override
    public int addFeedModel(ForumFeedPO feed)
    {
        String sql = String.format("INSERT INTO %s(post_id,meta_info,content) VALUE(?,?,?)",table_name);
        return this.getJdbcTemplate().update(sql, feed.getPost_id(), feed.getMeta_info(), feed.getContent());
    }

    @Override
    public int updateFeedModel(int feed_id, ForumFeedPO feed)
    {
        List<Object> params = new ArrayList<Object>();
        List<String> sets = new ArrayList<String>();
        String sql = String.format("UPDATE %s SET ", table_name);
        if ( feed.getPost_id() != null ) { params.add(feed.getPost_id()); sets.add(" post_id=? "); }
        if ( feed.getMeta_info() != null ) { params.add(feed.getMeta_info()); sets.add(" meta_info=? "); }
        if ( feed.getContent() != null ) { params.add(feed.getContent()); sets.add(" content=? "); }
        sql += StringUtils.join(sets,",");
        sql += " WHERE id=? ";
        params.add(feed_id);
        return this.getJdbcTemplate().update(sql,params.toArray());
    }

    @Override
    public int deleteFeedModel(int feed_id)
    {
        String sql = String.format("DELETE FROM %s WHERE id=?",table_name);
        return this.getJdbcTemplate().update(sql,feed_id);
    }

    @Override
    public int deleteFeedModels(int post_id)
    {
        String sql = String.format("DELETE FROM %s WHERE post_id=?",table_name);
        return this.getJdbcTemplate().update(sql,post_id);
    }

    @Override
    public int getNumOfFeeds(int post_id)
    {
        String sql = String.format("SELECT COUNT(*) FROM %s WHERE post_id=?",table_name);
        return this.getJdbcTemplate().queryForInt(sql, post_id);
    }
}
