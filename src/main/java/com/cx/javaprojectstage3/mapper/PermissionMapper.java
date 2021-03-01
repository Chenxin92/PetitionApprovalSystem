package com.cx.javaprojectstage3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.javaprojectstage3.bean.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sora
 */
@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据角色ID获取所有权限
     * @param roleId
     * @return
     */
    List<Permission> getPermissionListByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色确认权限
     * @param roleId
     * @param permissionId
     * @return
     */
    Integer getRolePermissionCount(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    /**
     * 根据角色ID和权限ID添加角色权限
     *
     * @param permissionId
     * @param roleId
     * @return
     */
    Integer addPermissionByIdAndRoleId(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    /**
     * 根据角色ID和权限ID删除角色权限
     *
     * @param permissionId
     * @param roleId
     * @return
     */
    Integer deletePermissionByIdAndRoleId(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    /**
     * 是否角色具备权限
     *
     * @return
     */
    Integer isPermissionByIdAndRoleId(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}