package com.cx.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Sora
 */
@Data
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

    public Advice() {
    }
}