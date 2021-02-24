package com.dfrz.javaprojectstage3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dfrz.javaprojectstage3.bean.Permission;
import com.dfrz.javaprojectstage3.service.IPermissionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 作者：zhengyefeng
 * 日期: 2021/2/20 0:53
 * 描述:
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    IPermissionService permissionService;

    /**
     * 跳转树形菜单页面
     */
    @RequestMapping("/toTree")
    public ModelAndView toTree() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tree");
        return mv;
    }

    /**
     * 获取数据库权限树
     *
     * @return
     */
    @RequestMapping("/tree")
    public String treeString(@Param("roleId") Integer roleId) {
        return permissionService.getPermissionTreeString(roleId);
    }

    @RequestMapping("/updatePermission")
    public String updatePermission(String objectJSON) {
        // 解析JSON对象
        JSONObject jsonObject = JSON.parseObject(objectJSON);

        JSONObject permission = jsonObject.getJSONObject("data");

        // 获取权限ID
        Integer permissionId = permission.getInteger("id");

        // 需要修改权限的角色ID
        Integer roleId = jsonObject.getInteger("roleId");

        // 添加/删除权限
        Boolean checked = jsonObject.getBoolean("checked");

        Boolean temp = permissionService.isPermissionByIdAndRoleId(roleId, permissionId);

        if (checked == true && temp == false) {

            return "" + permissionService.addPermissionByIdAndRoleId(roleId, permissionId);
        } else if (checked == false && temp == true) {
            return "" + permissionService.deletePermissionByIdAndRoleId(roleId, permissionId);
        }

        return "";
    }


}
