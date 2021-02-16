package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@TableName(value = "t_petition")
public class Petition {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date acceptTime;

    private String reflectName;

    private String bereflectName;

    private String bereflectPost;

    private String bereflectDepartment;

    private Integer contentType;

    private Integer petitionType;

    private Integer petitionState;

    private String stateDetail;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    private Integer userId;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getReflectName() {
        return reflectName;
    }

    public void setReflectName(String reflectName) {
        this.reflectName = reflectName == null ? null : reflectName.trim();
    }

    public String getBereflectName() {
        return bereflectName;
    }

    public void setBereflectName(String bereflectName) {
        this.bereflectName = bereflectName == null ? null : bereflectName.trim();
    }

    public String getBereflectPost() {
        return bereflectPost;
    }

    public void setBereflectPost(String bereflectPost) {
        this.bereflectPost = bereflectPost == null ? null : bereflectPost.trim();
    }

    public String getBereflectDepartment() {
        return bereflectDepartment;
    }

    public void setBereflectDepartment(String bereflectDepartment) {
        this.bereflectDepartment = bereflectDepartment == null ? null : bereflectDepartment.trim();
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getPetitionType() {
        return petitionType;
    }

    public void setPetitionType(Integer petitionType) {
        this.petitionType = petitionType;
    }

    public Integer getPetitionState() {
        return petitionState;
    }

    public void setPetitionState(Integer petitionState) {
        this.petitionState = petitionState;
    }

    public String getStateDetail() {
        return stateDetail;
    }

    public void setStateDetail(String stateDetail) {
        this.stateDetail = stateDetail == null ? null : stateDetail.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Petition{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", acceptTime=" + acceptTime +
                ", reflectName='" + reflectName + '\'' +
                ", bereflectName='" + bereflectName + '\'' +
                ", bereflectPost='" + bereflectPost + '\'' +
                ", bereflectDepartment='" + bereflectDepartment + '\'' +
                ", contentType=" + contentType +
                ", petitionType=" + petitionType +
                ", petitionState=" + petitionState +
                ", stateDetail='" + stateDetail + '\'' +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                '}';
    }
}