package org.buaa.cms.po;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stupid-coder on 6/28/16.
 */
public class ContentPO implements java.io.Serializable, RowMapper<ContentPO> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String meta_info;
    private String title;
    private String content;
    private String type;
    private String status;
    private String create_time;
    private String modify_time;

    @Override
    public ContentPO mapRow(ResultSet resultSet, int i) throws SQLException {
        ContentPO content = new ContentPO();

        try {
            content.setId(resultSet.getString("id"));
        } catch (SQLException e) {
            content.setId(null);
        }

        try {
            content.setMeta_info(resultSet.getString("meta_info"));
        } catch (SQLException e) {
            content.setMeta_info(null);
        }

        try {
            content.setTitle(resultSet.getString("title"));
        } catch (SQLException e) {
            content.setTitle(null);
        }

        try {
            content.setContent(resultSet.getString("content"));
        } catch (SQLException e) {
            content.setContent(null);
        }

        try {
            content.setType(resultSet.getString("type"));
        } catch (SQLException e) {
            content.setTitle(null);
        }

        try {
            content.setStatus(resultSet.getString("status"));
        } catch (SQLException e) {
            content.setStatus(null);
        }

        try {
            content.setCreate_time(resultSet.getString("create_time"));
        } catch (SQLException e) {
            content.setCreate_time(null);
        }

        return content;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "ContentPO{" +
                "id='" + id + '\'' +
                ", meta_info='" + meta_info + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", create_time='" + create_time + '\'' +
                ", modify_time='" + modify_time + '\'' +
                '}';
    }
}
