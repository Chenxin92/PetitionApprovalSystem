package com.dfrz.javaprojectstage3.bean;

public class Dictionary {
    private Integer id;

    private String typeKey;

    private String typeName;

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

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", typeKey='" + typeKey + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}