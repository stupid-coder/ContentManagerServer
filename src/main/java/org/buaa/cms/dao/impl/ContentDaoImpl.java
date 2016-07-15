package org.buaa.cms.dao.impl;

import org.buaa.cms.dao.ContentDao;
import org.buaa.cms.po.ContentPO;
import org.springframework.dao.DataAccessException;
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
    public List<ContentPO> getContentModels(String type, String status, int size) {
        String sql;
        if ( type.compareTo("*") == 0 || status.compareTo("*") == 0 ) {
            if (size <= 0)
                sql = String.format("SELECT id,meta_info,title,type,status,,create_time FROM %s WHERE type=? and status=? ORDER BY create_time DESC", table_name);
            else
                sql = String.format("SELECT id,meta_info,title,type,status,create_time FROM %s WHERE type=? and status=? ORDER BY create_time DESC LIMIT %d", table_name, size);
            return this.getJdbcTemplate().query(sql, new ContentPO(), type, status);
        } else {
            sql = String.format("SELECT id,meta_info,title,type,status,create_time FROM %s ORDER BY create_time DESC",table_name);
            return this.getJdbcTemplate().query(sql, new ContentPO());
        }
    }

    @Override
    @Transactional
    public ContentPO getContentModel(int id) {
        return getContentById(id);
    }

    @Override
    @Transactional
    public int addContentModel(ContentPO content) {
        String sql;
        if ( content.getStatus() == null ) {
            sql = String.format("INSERT INTO %s(meta_info,title,content,type) VALUES(?,?,?,?)", table_name);
            return this.getJdbcTemplate().update(sql, content.getMeta_info(), content.getTitle(), content.getContent(), content.getType());
        }
        else {
            sql = String.format("INSERT INTO %s(meta_info,title,content,type,status) VALUES(?,?,?,?,?)", table_name);
            return this.getJdbcTemplate().update(sql, content.getMeta_info(), content.getTitle(),content.getContent(),content.getType(),content.getStatus());
        }
    }

    @Override
    @Transactional
    public int deleteContentModel(int id) {
        String sql = String.format("DELETE FROM %s WHERE id=?",table_name);
        return this.getJdbcTemplate().update(sql,id);
    }

    @Override
    @Transactional
    public int updateContentModel(int id, String status) {
        String sql = String.format("UPDATE %s SET status=? WHERE id=?",table_name);
        return this.getJdbcTemplate().update(sql,status,id);
    }

    @Override
    @Transactional
    public ContentPO getContentById(int id) {
        String sql = String.format("SELECT * FROM %s WHERE id=?",table_name);
        try {
            return this.getJdbcTemplate().queryForObject(sql, new ContentPO(), id);
        } catch ( DataAccessException e) {
            return null;
        }
    }
}
