package com.cx.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Sora
 */
@Data
@TableName(value = "t_users")
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String realname;

    private String sex;

    private String password;

    private String phone;

    private String email;

    private String headpic;

    private String department;

    private String address;

    private String status;

    private Integer role;

    /**
     * 不是数据库里的字段
     */
    @TableField(exist = false)
    private Role urole;

    private Date createtime;

    private Date updatetime;

    public User() {
    }

    public User(String username, String realname) {
        this.username = username;
        this.realname = realname;
    }

    public User(Integer id, String username, String realname) {
        this.id = id;
        this.username = username;
        this.realname = realname;
    }

    public User(Integer id, String username, String realname, String sex, String phone, String email, String department, String address, String status) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.address = address;
        this.status = status;
    }

    public User(Integer id, String username, String realname, String sex, String password, String phone, String email, String headpic, String department, Integer role, Role urole, Date createtime, Date updatetime) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.sex = sex;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.headpic = headpic;
        this.department = department;
        this.role = role;
        this.urole = urole;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public User(Integer id, String username, String realname, String sex, String password, String phone, String email, String headpic, String department, String address, String status, Integer role, Role urole, Date createtime, Date updatetime) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.sex = sex;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.headpic = headpic;
        this.department = department;
        this.address = address;
        this.status = status;
        this.role = role;
        this.urole = urole;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }
}