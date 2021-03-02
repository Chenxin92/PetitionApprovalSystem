package com.cx.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Sora
 */
@Data
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

    public Petition() {
    }
}