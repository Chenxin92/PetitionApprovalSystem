package com.cx.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName(value = "t_role")
public class Role {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "rolekey")
    private String roleKey;
    @TableField(value = "roleName")
    private String roleName;

    @TableField(exist = false)
    private List<Permission> permissions;

    public Role(Integer id, String roleKey, String roleName) {
        this.id = id;
        this.roleKey = roleKey;
        this.roleName = roleName;
    }

    public Role(Integer id, String roleKey, String roleName, List<Permission> permissions) {
        this.id = id;
        this.roleKey = roleKey;
        this.roleName = roleName;
        this.permissions = permissions;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleKey='" + roleKey + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}