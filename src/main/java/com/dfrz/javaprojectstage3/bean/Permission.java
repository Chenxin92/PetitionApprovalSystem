package com.dfrz.javaprojectstage3.bean;

import com.alibaba.fastjson.JSONObject;
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
    private String title;

    private String url;

    private Integer pid;
    @TableField(value = "permissionKey")
    private String permissionKey;


    public Permission(Integer id, String title, Integer pid, String permissionKey) {
        this.id = id;
        this.title = title;
        this.pid = pid;
        this.title = permissionKey;
    }

    public Permission(Integer id, String title, String url, Integer pid, String permissionKey, List<Permission> children, boolean checked) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.pid = pid;
        this.permissionKey = permissionKey;
        this.children = children;
        this.checked = checked;
    }

    public Permission(Integer id, String title, String url, Integer pid, String permissionKey) {
        this.id = id;
        this.title = title;
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

    /**
     * 节点
     * @param args
     */
    public static void main(String[] args) {
        Permission root = new Permission(0, "顶层节点", -1,"top");
        Permission node = null;
        node=new Permission(1,"用户管理",0,"user");
        root.add(node);
        node=new Permission(2,"信访件管理",0,"information");
        root.add(node);
        node=new Permission(3,"公告管理",0,"notice");
        root.add(node);
        node=new Permission(4,"用户列表",1,"user_list");
        root.add(node);
        node=new Permission(5,"编辑用户",1,"user_update");
        root.add(node);

        node=new Permission(5,"删除用户",1,"user_delete");
        root.add(node);
        node=new Permission(6,"信访件发布",2,"information-add");
        root.add(node);
        node=new Permission(7,"信访件删除",2,"information-delete-add");
        root.add(node);
        node=new Permission(8,"信访件查询",2,"information-list");
        root.add(node);
        node=new Permission(9,"信访件处理",2,"information-handle");
        root.add(node);
        node=new Permission(10,"添加用户",1,"user_add");
        root.add(node);
        //转JSON  GSON、fastjson
        String json=JSONObject.toJSONString(root);
        System.out.println(json);



    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettitle() {
        return title;
    }

    public void setPermissionName(String permissionName) {
        this.title = permissionName;
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
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
 @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", pid=" + pid +
                ", permissionKey='" + permissionKey + '\'' +
                ", children=" + children +
                ", checked=" + checked +
                '}';
    }
}