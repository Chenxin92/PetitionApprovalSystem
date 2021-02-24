package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sora
 */
@TableName(value = "t_permissions")
public class Permission {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "permissionKey")
    private String permissionKey;

    @TableField(value = "permissionName")
    private String title;

    private String url;

    /**
     * 父节点ID
     */
    private Integer pid;

    /**
     * 节点集合
     */
    @TableField(exist = false)
    private List<Permission> children = new ArrayList<>();

    /**
     * 是否被选中
     */
    @TableField(exist = false)
    private Boolean checked = false;

    /**
     * 递归添加节点
     * @param node
     */
    public void addNode(Permission node) {
        // 顶级节点
        if (node.pid ==0) {
            this.children.add(node);
        }
        // 同级节点
        else if (node.pid.equals(this.id)) {
            this.children.add(node);
        }
        // 搜查对应节点
        else {
            for (Permission tmpNode : children) {
                tmpNode.addNode(node);
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Permission() {
    }

    public Permission(Integer id, String permissionKey, String title, Integer pid) {
        this.id = id;
        this.permissionKey = permissionKey;
        this.title = title;
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionKey='" + permissionKey + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", pid=" + pid +
                ", children=" + children +
                ", checked=" + checked +
                '}';
    }
}