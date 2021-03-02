package com.cx.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author Sora
 */
@Data
@TableName(value = "t_dictionary")
public class Dictionary {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "type_key")
    private String typeKey;
    @TableField(value = "type_name")
    private String typeName;
    /**
     * 字典资料列表
     */
    @TableField(exist = false)
    private List<DictionaryData> dictionaryDataList;

    public Dictionary() {
    }
}