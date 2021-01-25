package com.dfrz.javaprojectstage3.bean;

import java.util.Date;

public class Advice {
    private Integer id;

    private String createUser;

    private Date createTime;

    private String status;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id=" + id +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}