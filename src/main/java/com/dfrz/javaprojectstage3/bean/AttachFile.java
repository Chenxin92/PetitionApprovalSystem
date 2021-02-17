package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 附件
 * @author Sora
 */
@TableName(value = "t_file")
public class AttachFile {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 附件所属对象ID
     */
    private Integer objectId;
    /**
     * 附件属性
     * 0-信访附件 1-公告附件 2-审批意见附件
     */
    private Integer fileType;
    /**
     * 附件名称
     */
    private String fileName;
    /**
     * 附件路径
     */
    private String filePath;
    /**
     * 附件类型
     * 0-图片 1-文档PDF
     */
    private Integer type;
    /**
     * 附件状态
     */
    private String state;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AttachFile{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", fileType=" + fileType +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", type=" + type +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}