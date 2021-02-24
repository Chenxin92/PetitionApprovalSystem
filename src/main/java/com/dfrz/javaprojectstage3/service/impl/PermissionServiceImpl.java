package com.dfrz.javaprojectstage3.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dfrz.javaprojectstage3.bean.Permission;
import com.dfrz.javaprojectstage3.mapper.PermissionMapper;
import com.dfrz.javaprojectstage3.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sora
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public String getPermissionTreeString(Integer roleId) {
        // 获取所有权限集合
        List<Permission> permissionList = permissionMapper.selectList(null);
        // 初始化权限树对象
        Permission permissionTree = new Permission();
        // 权限节点
        Permission node;
        // 添加节点
        for (Permission permission : permissionList) {
            node = new Permission(permission.getId(), permission.getPermissionKey(), permission.getTitle(), permission.getPid());
            // 根据角色确认是否存在该权限
            Integer result = permissionMapper.getRolePermissionCount(roleId, permission.getId());
            if (result > 0) {
                node.setChecked(true);
            }
            // 将节点添加至树中
            permissionTree.addNode(node);
        }
        return JSONObject.toJSONString(permissionTree);
    }

    @Override
    public Boolean addPermissionByIdAndRoleId(Integer roleId, Integer id) {
        return permissionMapper.addPermissionByIdAndRoleId(roleId, id) > 0;
    }

    @Override
    public Boolean deletePermissionByIdAndRoleId(Integer roleId, Integer id) {
        return permissionMapper.deletePermissionByIdAndRoleId(roleId, id) > 0;
    }

    @Override
    public Boolean isPermissionByIdAndRoleId(Integer roleId, Integer id) {
        return permissionMapper.isPermissionByIdAndRoleId(roleId, id) > 0;
    }
}
