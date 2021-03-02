package com.cx.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 附件
 * @author Sora
 */
@Data
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

    public AttachFile() {
    }
}