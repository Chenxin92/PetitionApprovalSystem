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
    @TableField(value = "permissionName")
    private String permissionName;

    private String url;

    private Integer pid;
    @TableField(value = "permissionKey")
    private String permissionKey;


    public Permission(Integer id, String permissionName, Integer pid, String permissionKey) {
        this.id = id;
        this.permissionName = permissionName;
        this.pid = pid;
        this.permissionKey = permissionKey;
    }

    public Permission(Integer id, String permissionName, String url, Integer pid, String permissionKey, List<Permission> children, boolean checked) {
        this.id = id;
        this.permissionName = permissionName;
        this.url = url;
        this.pid = pid;
        this.permissionKey = permissionKey;
        this.children = children;
        this.checked = checked;
    }

    public Permission(Integer id, String permissionName, String url, Integer pid, String permissionKey) {
        this.id = id;
        this.permissionName = permissionName;
        this.url = url;
        this.pid = pid;
        this.permissionKey = permissionKey;
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



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
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

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                ", pid=" + pid +
                ", permissionKey='" + permissionKey + '\'' +
                ", children=" + children +
                ", checked=" + checked +
                '}';
    }
}