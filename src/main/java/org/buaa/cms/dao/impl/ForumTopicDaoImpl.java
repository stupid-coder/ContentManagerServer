package org.buaa.cms.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.buaa.cms.dao.ForumTopicDao;
import org.buaa.cms.po.ForumTopicPO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stupid-coder on 11/19/16.
 */
public class ForumTopicDaoImpl extends JdbcDaoSupport implements ForumTopicDao {

    static final String table_name = "Topic";

    @Resource
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<ForumTopicPO> getTopicModels() {
        String sql = String.format("SELECT id,title,content,creat_time FROM %s ORDER BY id", table_name);
        return this.getJdbcTemplate().query(sql, new ForumTopicPO());
    }

    @Override
    public ForumTopicPO getTopicModel(int id) {
        return getTopicById(id);
    }

    @Override
    @Transactional
    public int addTopicModel(ForumTopicPO topic) {
        String sql = String.format("INSERT INTO %s(meta_info,title,content) VALUE(?,?,?)", table_name);
        return this.getJdbcTemplate().update(sql, topic.getMeta_info(), topic.getTitle(), topic.getContent());
    }

    @Override
    public int deleteTopicModel(int id)
    {
        String sql = String.format("DELETE FROM %s WHERE id=?",table_name);
        return this.getJdbcTemplate().update(sql,id);
    }

    @Override
    public int updateTopicModel(int id, ForumTopicPO topic)
    {
        List<Object> params = new ArrayList<Object>();
        List<String> sets = new ArrayList<String>();

        String sql = String.format("UPDATE %s SET ",table_name);
        if ( topic.getMeta_info() != null ) { params.add(topic.getMeta_info()); sets.add(" meta_info=? "); }
        if ( topic.getTitle() != null ) { params.add(topic.getTitle()); sets.add(" title=? "); }
        if ( topic.getContent() != null ) { params.add(topic.getContent()); sets.add(" content=? "); }
        sql += StringUtils.join(sets,",");
        sql += " WHERE id=? ";
        params.add(id);
        return this.getJdbcTemplate().update(sql,params.toArray());
    }

    @Override
    public ForumTopicPO getTopicById(int id)
    {
        String sql = String.format("SELECT id,title,content,creat_time FROM %s WHERE id=? ", table_name);
        try {
            return this.getJdbcTemplate().queryForObject(sql, new ForumTopicPO(), id);
        } catch( DataAccessException e) {
            return null;
        }
    }
}
