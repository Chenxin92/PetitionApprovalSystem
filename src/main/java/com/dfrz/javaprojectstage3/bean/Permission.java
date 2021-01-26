package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

@TableName(value = "t_permissions")
public class Permission {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String url;

    private Integer pid;

    private String key;

    private String permissionkey;

    public Permission(Integer id, String name, String url, Integer pid, String key, String permissionkey) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.pid = pid;
        this.key = key;
        this.permissionkey = permissionkey;
    }

    //子节点
    @TableField(exist = false)
    List<Permission> children =new ArrayList<>();
    //是否被选中
    @TableField(exist = false)
    boolean checked=false;

    /*
     * 递归添加节点
     * */
    public void add(Permission node) {
        if ("0".equals(node.pid)) {
            this.children.add(node);
        } else if (node.pid.equals(this.id)) {
            this.children.add(node);
        } else {
            for (Permission tmp_node : children) {
                tmp_node.add(node);
            }
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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
                ", pid=" + pid +
                ", key='" + key + '\'' +
                ", permissionkey='" + permissionkey + '\'' +
                '}';
    }
}