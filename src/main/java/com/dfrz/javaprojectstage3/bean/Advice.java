package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;

@TableName(value = "t_advice")
public class Advice {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer createUser;

    private Date createTime;

    private String status;

    private String content;

    /**
     * 信访件附件列表
     */
    @TableField(exist = false)
    private List<AttachFile> attachFileList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
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

    public List<AttachFile> getAttachFileList() {
        return attachFileList;
    }

    public void setAttachFileList(List<AttachFile> attachFileList) {
        this.attachFileList = attachFileList;
    }

    public Advice(Integer id, Integer createUser, Date createTime, String status, String content, List<AttachFile> attachFileList) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.status = status;
        this.content = content;
        this.attachFileList = attachFileList;
    }

    public Advice() {
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id=" + id +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                ", attachFileList=" + attachFileList +
                '}';
    }
}