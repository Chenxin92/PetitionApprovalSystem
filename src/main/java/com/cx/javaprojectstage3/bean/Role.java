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

    public Role() {
    }

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
}