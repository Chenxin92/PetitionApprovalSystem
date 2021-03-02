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
@TableName(value = "t_step")
public class Step {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer petitionId;

    private Date auditTime1;

    private Date auditTime2;

    private Date auditTime3;

    private Integer examineUser1;

    private Integer examineUser2;

    private Integer examineUser3;

    private Date createtime;

    public Step() {
    }

    public Step(Integer id, Integer petitionId, Date auditTime1, Date auditTime2, Date auditTime3, Integer examineUser1, Integer examineUser2, Integer examineUser3, Date createtime) {
        this.id = id;
        this.petitionId = petitionId;
        this.auditTime1 = auditTime1;
        this.auditTime2 = auditTime2;
        this.auditTime3 = auditTime3;
        this.examineUser1 = examineUser1;
        this.examineUser2 = examineUser2;
        this.examineUser3 = examineUser3;
        this.createtime = createtime;
    }
}