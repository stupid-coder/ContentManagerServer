package org.buaa.cms.dao.impl;

import org.buaa.cms.dao.ContentDao;
import org.buaa.cms.po.ContentPO;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by stupid-coder on 6/28/16.
 */
@Repository
public class ContentDaoImpl extends JdbcDaoSupport implements ContentDao {

    static final String table_name = "Content";

    @Resource
    private DataSource dataSource;

    @PostConstruct
    private void initialize()
    {
        setDataSource(dataSource);
    }

    @Override
    @Transactional
    public List<ContentPO> getContentModel(String type, String status) {
        String sql = String.format("SELECT * FROM %s WHERE type=? and status=?",table_name);
        return this.getJdbcTemplate().query(sql, new ContentPO(), type, status);
    }

    @Override
    @Transactional
    public ContentPO getContentModel(int id) {
        return getContentById(id);
    }

    @Override
    @Transactional
    public void addContentModel(ContentPO content) {
        String sql = String.format("INSERT INTO %s(title,content,type,status) VALUES(?,?,?,?)",table_name);
        this.getJdbcTemplate().update(sql,content.getTitle(),content.getContent(),content.getType(),content.getStatus());
    }

    @Override
    @Transactional
    public void deleteContentModel(int id) {
        String sql = String.format("DELETE FROM %s WHERE id=?",table_name);
        this.getJdbcTemplate().update(sql,id);
    }

    @Override
    @Transactional
    public void updateContentModel(int id, String status) {
        String sql = String.format("UPDATE %s SET status=? WHERE id=?",table_name);
        this.getJdbcTemplate().update(sql,status,id);
    }

    @Override
    @Transactional
    public ContentPO getContentById(int id) {
        String sql = String.format("SELECT %s FROM Content WHERE id=?",table_name);
        return this.getJdbcTemplate().queryForObject(sql,new ContentPO(),id);
    }
}
