package org.buaa.cms.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.buaa.cms.dao.ForumPostDao;
import org.buaa.cms.po.ForumPostPO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stupid-coder on 11/19/16.
 */
@Repository
public class ForumPostDaoImpl extends JdbcDaoSupport implements ForumPostDao {

    static final String table_name = "Post";

    @Resource
    private DataSource dataSource;

    @PostConstruct
    private void initialize() { setDataSource(dataSource); }

    @Override
    public List<ForumPostPO> getPostModels(int topic_id, int page, int page_size)
    {
        String sql = String.format("SEELCT * FROM %s WHERE topic_id=? limit %d.%d",table_name,page*page_size,page_size);
        return this.getJdbcTemplate().query(sql,new ForumPostPO(), topic_id);
    }

    @Override
    public ForumPostPO getPostModel(int id)
    {
        return getPostModelById(id);
    }

    @Override
    public int addPostModel(ForumPostPO post)
    {
        String sql = String.format("INSERT INTO %s(topic_id,meta_info,title,content) VALUES(?,?,?,?)",table_name);
        return this.getJdbcTemplate().update(sql,post.getTopic_id(),post.getMeta_info(),post.getTitle(),post.getContent());
    }

    @Override
    public int deletePostModels(int topic_id)
    {
        String sql = String.format("DELETE FROM %s WEHRE topic_id=?",table_name);
        return this.getJdbcTemplate().update(sql,topic_id);
    }

    @Override
    public int deletePostModel(int id)
    {
        String sql = String.format("DELETE FROM %s WHERE id=?",table_name);
        return this.getJdbcTemplate().update(sql,id);
    }

    @Override
    public int updatePostModel(int id, ForumPostPO post)
    {
        List<Object> params = new ArrayList<Object>();
        List<String> sets = new ArrayList<String>();
        String sql = String.format("UPDATE %s SET ",table_name);
        if ( post.getTopic_id() != null ) { params.add(post.getTopic_id()); sets.add(" topic_id=? "); }
        if ( post.getMeta_info() != null ) { params.add(post.getMeta_info()); sets.add(" meta_info=? "); }
        if ( post.getTitle() != null ) { params.add(post.getMeta_info()); sets.add(" title=? "); }
        if ( post.getContent() != null ) { params.add(post.getContent()); sets.add(" content=? "); }
        sql += StringUtils.join(sets,",");
        sql += " WHERE id=? ";
        params.add(id);
        return this.getJdbcTemplate().update(sql,params.toArray());
    }

    private ForumPostPO getPostModelById(int id)
    {
        String sql = String.format("SELECT * FROM %s WHERE id=?",table_name);
        try {
            return this.getJdbcTemplate().queryForObject(sql,new ForumPostPO(),id);
        } catch(DataAccessException e) {
            return null;
        }
    }

    @Override
    public int getNumOfPosts(int topic_id)
    {
        String sql = String.format("SELECT COUNT(*) FROM %s WHERE topic_id=?",table_name);
        try {
            return this.getJdbcTemplate().queryForInt(sql,topic_id);
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
