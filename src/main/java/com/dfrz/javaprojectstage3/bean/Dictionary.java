package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey == null ? null : typeKey.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public List<DictionaryData> getDictionaryDataList() {
        return dictionaryDataList;
    }

    public void setDictionaryDataList(List<DictionaryData> dictionaryDataList) {
        this.dictionaryDataList = dictionaryDataList;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", typeKey='" + typeKey + '\'' +
                ", typeName='" + typeName + '\'' +
                ", dictionaryDataList=" + dictionaryDataList +
                '}';
    }
}