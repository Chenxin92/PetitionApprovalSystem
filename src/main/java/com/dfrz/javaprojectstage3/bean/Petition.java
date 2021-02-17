package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @author Sora
 */
@TableName(value = "t_petition")
public class Petition {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 编号
     */
    private String code;
    /**
     * 收件时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date acceptTime;
    /**
     * 反映人姓名
     */
    private String reflectName;
    /**
     * 被反映人姓名
     */
    private String bereflectName;
    /**
     * 被反映人岗位
     */
    private String bereflectPost;
    /**
     * 被反映人部门
     */
    private String bereflectDepartment;
    /**
     * 内容分类
     */
    private Integer contentType;
    /**
     * 信源分类
     */
    private Integer petitionType;
    /**
     * 审批状态
     * 0；草稿；1一级审批中；2二级审批中；-1退回，-2通过
     */
    private Integer petitionState;

    private String stateDetail;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;
    /**
     * 信访件创建人ID
     */
    private Integer userId;
    /**
     * 举报内容
     */
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

    public List<AttachFile> getAttachFileList() {
        return attachFileList;
    }

    public void setAttachFileList(List<AttachFile> attachFileList) {
        this.attachFileList = attachFileList;
    }

    public Petition() {
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
                ", attachFileList=" + attachFileList +
                '}';
    }
}