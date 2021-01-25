package com.dfrz.javaprojectstage3.bean;

public class Data {
    private Integer id;

    private Integer dictionaryId;

    private String dictionaryContent;

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

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", dictionaryId=" + dictionaryId +
                ", dictionaryContent='" + dictionaryContent + '\'' +
                ", value=" + value +
                '}';
    }
}