package org.buaa.cms.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.buaa.cms.dao.ContentDao;
import org.buaa.cms.po.ContentPO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

        List<String> where = new ArrayList<String>();
        List<Object> params = new ArrayList<Object>();

        if ( type.compareTo("*") != 0 ) { where.add(" type=? "); params.add(type); }
        if ( status.compareTo("*") != 0 ) { where.add(" status=? "); params.add(status); }

        if ( size <= 0 ) {
            size = 999;
        }

        String sql;
        if ( where.size() != 0 ) {
            sql = String.format("SELECT id,meta_info,title,type,status,create_time FROM %s WHERE %s ORDER BY create_time DESC LIMIT %d", table_name, StringUtils.join(where, " AND "), size);
            return this.getJdbcTemplate().query(sql, new ContentPO(), params.toArray());
        } else {
            sql = String.format("SELECT id,meta_info,title,type,status,create_time FROM %s ORDER BY create_time DESC LIMIT %d", table_name, size);
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
            sql = String.format("INSERT INTO %s(meta_info,title,content,type,create_time) VALUES(?,?,?,?,?)", table_name);
            return this.getJdbcTemplate().update(sql, content.getMeta_info(), content.getTitle(), content.getContent(), content.getType(),new Date());
        }
        else {
            sql = String.format("INSERT INTO %s(meta_info,title,content,type,status,create_time) VALUES(?,?,?,?,?,?)", table_name);
            return this.getJdbcTemplate().update(sql, content.getMeta_info(), content.getTitle(),content.getContent(),content.getType(),content.getStatus(),new Date());
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
    public int updateContentModel(int id, ContentPO content) {
        List<Object> params = new ArrayList<Object>();
        List<String> sets = new ArrayList<String>();
        String sql = String.format("UPDATE %s SET ",table_name);
        if ( content.getContent() != null ) { params.add(content.getContent());  sets.add(" content=? "); }
        if ( content.getMeta_info() != null ) { params.add(content.getMeta_info()); sets.add(" meta_info=? "); }
        if ( content.getStatus() != null ) { params.add(content.getStatus()); sets.add(" status=? "); }
        if ( content.getTitle() != null ) { params.add(content.getTitle()); sets.add(" title=? "); }
        if ( content.getType() != null )  { params.add(content.getType()); sets.add(" type=? "); }
        sql += StringUtils.join(sets,",");
        sql += " WHERE id=? ";
        params.add(id);
        return this.getJdbcTemplate().update(sql,params.toArray());
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

    @Override
    public List<ContentPO> recContentModels(String type, String status, int id, int size)
    {
        ContentPO content = getContentById(id);

        List<Object> params = new ArrayList<Object>();
        List<String> where = new ArrayList<String>();

        if ( type.compareTo("*") != 0 ) { where.add(" type=? "); params.add(type); }
        if ( status.compareTo("*") != 0 ) { where.add(" status=? "); params.add(status); }

        if ( content != null ) {
            Timestamp ts = Timestamp.valueOf(content.getCreate_time());
            where.add(" create_time>=? "); params.add(new Timestamp(ts.getTime()-15*3600*24*1000L));
            where.add(" create_time<=? "); params.add(new Timestamp(ts.getTime()+15*3600*24*1000L));
        }

        if ( size == 0 ) size = 999;

        String sql = String.format("SELECT id,meta_info,title,type,status,create_time FROM %s WHERE %s ORDER BY create_time DESC LIMIT %d ",table_name, StringUtils.join(where, " AND "),size);
        return this.getJdbcTemplate().query(sql, new ContentPO(), params.toArray());
    }

    @Override
    public ContentPO prevnextContentModel(String type, String status, int id, int flag) {
        List<Object> params = new ArrayList<Object>();
        List<String> where = new ArrayList<String>();
        List<ContentPO> contentPOList;
        if ( type.compareTo("*") != 0 ) { where.add(" type=? "); params.add(type); }
        if ( type.compareTo("*") != 0 ) { where.add(" status=? "); params.add(status); }

        params.add(id);
        if ( flag == -1 ) {
            where.add(" id<? ");
            String sql = String.format("SELECT id from %s WHERE %s ORDER BY id DESC LIMIT 1", table_name,StringUtils.join(where," AND "));
            contentPOList = this.getJdbcTemplate().query(sql,new ContentPO(), params.toArray());
        } else {
            where.add(" id>? ");
            String sql = String.format("SELECT id from %s WHERE %s ORDER BY id LIMIT 1", table_name,StringUtils.join(where," AND "));
            contentPOList = this.getJdbcTemplate().query(sql,new ContentPO(), params.toArray());
        }

        if ( contentPOList.size() == 1 )
            return contentPOList.get(0);
        return null;
    }
}
