package com.cx.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Sora
 */
@Data
@TableName(value = "t_notice")
public class Notice {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String title;

    private String content;

    private String type;

    private Integer userId;

    private String createUser;

    private Date createTime;

    public Notice() {
    }
}