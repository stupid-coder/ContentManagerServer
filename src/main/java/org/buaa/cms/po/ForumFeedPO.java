package org.buaa.cms.po;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stupid-coder on 11/20/16.
 */
public class ForumFeedPO implements java.io.Serializable, RowMapper<ForumFeedPO> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String post_id;
    private String meta_info;
    private String content;
    private String create_time;
    private String modify_time;

    @Override
    public ForumFeedPO mapRow(ResultSet resultSet, int row) throws SQLException {
        ForumFeedPO feed = new ForumFeedPO();

        try {
            feed.setId(resultSet.getString("id"));
        } catch (SQLException e) {
            feed.setId(null);
        }

        try {
            feed.setPost_id(resultSet.getString("post_id"));
        } catch (SQLException e) {
            feed.setPost_id(null);
        }

        try {
            feed.setMeta_info(resultSet.getString("meta_info"));
        } catch (SQLException e) {
            feed.setMeta_info(null);
        }

        try {
            feed.setContent(resultSet.getString("content"));
        } catch ( SQLException e) {
            feed.setContent(null);
        }

        try {
            feed.setCreate_time(resultSet.getString("create_time"));
        } catch (SQLException e) {
            feed.setCreate_time(null);
        }

        try {
            feed.setModify_time(resultSet.getString("modify_time"));
        } catch (SQLException e) {
            feed.setModify_time(null);
        }

        return feed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getMeta_info() {
        return meta_info;
    }

    public void setMeta_info(String meta_info) {
        this.meta_info = meta_info;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    @Override
    public String toString() {
        return "ForumFeedPO{" +
                "id='" + id + '\'' +
                ", meta_info='" + meta_info + '\'' +
                ", content='" + content + '\'' +
                ", create_time='" + create_time + '\'' +
                ", modify_time='" + modify_time + '\'' +
                '}';
    }
}
