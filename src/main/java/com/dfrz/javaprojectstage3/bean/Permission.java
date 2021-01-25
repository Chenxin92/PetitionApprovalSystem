package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_permission")
public class Permission {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String url;

    private Integer ismenu;

    private Integer pid;

    private String permissionkey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(Integer ismenu) {
        this.ismenu = ismenu;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPermissionkey() {
        return permissionkey;
    }

    public void setPermissionkey(String permissionkey) {
        this.permissionkey = permissionkey == null ? null : permissionkey.trim();
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", ismenu=" + ismenu +
                ", pid=" + pid +
                ", permissionkey='" + permissionkey + '\'' +
                '}';
    }
}