package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private Integer role;

    //不是数据库里的字段
    @TableField(exist = false)
    private Role urole;

    private Date createtime;

    private Date updatetime;

    public User(Integer id, String username, String realname) {
        this.id = id;
        this.username = username;
        this.realname = realname;
    }

    public User() {
    }


    public User(String username, String realname) {
        this.username = username;
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", headpic='" + headpic + '\'' +
                ", department='" + department + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", role=" + role +
                ", urole=" + urole +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic == null ? null : headpic.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Role getUrole() {
        return urole;
    }

    public void setUrole(Role urole) {
        this.urole = urole;
    }
}