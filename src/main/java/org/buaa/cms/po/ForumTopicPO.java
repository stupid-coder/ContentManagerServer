package org.buaa.cms.po;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stupid-coder on 11/19/16.
 */
public class ForumTopicPO implements java.io.Serializable, RowMapper<ForumTopicPO> {

    private static final long serialVersionUID = -1L;

    private String id;
    private String meta_info;
    private String title;
    private String content;
    private String create_time;

    @Override
    public ForumTopicPO mapRow(ResultSet resultSet, int i) throws SQLException {
        ForumTopicPO topic = new ForumTopicPO();

        try {
            topic.setId(resultSet.getString("id"));
        } catch (SQLException e) {
            topic.setId(null);
        }

        try {
            topic.setMeta_info(resultSet.getString("meta_info"));
        } catch (SQLException e) {
            topic.setMeta_info(null);
        }

        try {
            topic.setTitle(resultSet.getString("title"));
        } catch (SQLException e) {
            topic.setTitle(null);
        }

        try {
            topic.setContent(resultSet.getString("content"));
        } catch (SQLException e) {
            topic.setContent(null);
        }

        try {
            topic.setCreate_time(resultSet.getString("create_time"));
        } catch (SQLException e) {
            topic.setCreate_time(null);
        }

        return topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ForumTopicPO{" +
                "id='" + id + '\'' +
                ", meta_info='" + meta_info + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
