package com.cx.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Sora
 */
@Data
@TableName(value = "t_data")
public class DictionaryData {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "dictionary_id")
    private Integer dictionaryId;

    @TableField(value = "dictionary_content")
    private String dictionaryContent;

    private Integer value;

    public DictionaryData() {
    }
}