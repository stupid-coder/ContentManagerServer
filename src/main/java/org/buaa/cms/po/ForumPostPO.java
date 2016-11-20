package org.buaa.cms.po;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stupid-coder on 11/19/16.
 */
public class ForumPostPO implements java.io.Serializable, RowMapper<ForumPostPO> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String topic_id;
    private String meta_info;
    private String title;
    private String content;
    private String create_time;
    private String modify_time;

    @Override
    public ForumPostPO mapRow(ResultSet resultSet, int i) throws SQLException {
        ForumPostPO post = new ForumPostPO();

        try {
            post.setId(resultSet.getString("id"));
        } catch (SQLException e) {
            post.setId(null);
        }

        try {
            post.setMeta_info(resultSet.getString("meta_info"));
        } catch (SQLException e) {
            post.setMeta_info(null);
        }

        try {
            post.setTitle(resultSet.getString("title"));
        } catch (SQLException e) {
            post.setTitle(null);
        }

        try {
            post.setContent(resultSet.getString("content"));
        } catch (SQLException e) {
            post.setContent(null);
        }

        try {
            post.setTopic_id(resultSet.getString("topic_id"));
        } catch (SQLException e) {
            post.setTopic_id(null);
        }

        try {
            post.setCreate_time(resultSet.getString("create_time"));
        } catch (SQLException e) {
            post.setCreate_time(null);
        }

        try {
            post.setModify_time(resultSet.getString("modify_time"));
        } catch (SQLException e) {
            post.setModify_time(null);
        }
        return post;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getMeta_info() {
        return meta_info;
    }

    public void setMeta_info(String meta_info) {
        this.meta_info = meta_info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "ForumPostPO{" +
                "id='" + id + '\'' +
                ", topic_id='" + topic_id + '\'' +
                ", meta_info='" + meta_info + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", create_time='" + create_time + '\'' +
                ", modify_time='" + modify_time + '\'' +
                '}';
    }
}
