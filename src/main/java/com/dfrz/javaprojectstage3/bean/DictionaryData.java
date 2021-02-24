package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author Sora
 */
@TableName(value = "t_data")
public class DictionaryData {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "dictionary_id")
    private Integer dictionaryId;
    @TableField(value = "dictionary_content")
    private String dictionaryContent;

    public DictionaryData(Integer id, Integer dictionaryId, String dictionaryContent, Integer value) {
        this.id = id;
        this.dictionaryId = dictionaryId;
        this.dictionaryContent = dictionaryContent;
        this.value = value;
    }

    public DictionaryData() {
    }

    @Override
    public String toString() {
        return "DictionaryData{" +
                "id=" + id +
                ", dictionaryId=" + dictionaryId +
                ", dictionaryContent='" + dictionaryContent + '\'' +
                ", value=" + value +
                '}';
    }

    private Integer value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getDictionaryContent() {
        return dictionaryContent;
    }

    public void setDictionaryContent(String dictionaryContent) {
        this.dictionaryContent = dictionaryContent == null ? null : dictionaryContent.trim();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}