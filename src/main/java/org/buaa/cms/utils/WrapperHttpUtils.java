package org.buaa.cms.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by stupid-coder on 7/15/16.
 */
public class WrapperHttpUtils {

    Object data;
    int status;
    String info;

    public WrapperHttpUtils(Object data) {
        this.status = HttpServletResponse.SC_OK;
        this.info = "success";
        this.data = data;
    }

    public WrapperHttpUtils(Object data, int status, String info) {
        this.status = status;
        this.info = info;
        this.data = data;
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "WrapperHttpUtils{" +
                "data=" + data +
                ", status=" + status +
                ", info='" + info + '\'' +
                '}';
    }
}
