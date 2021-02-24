package com.dfrz.javaprojectstage3.service;

/**
 * @author Sora
 */
public interface IPermissionService {
    /**
     * 获取数据库中权限树
     *
     * @param roleId
     * @return
     */
    String getPermissionTreeString(Integer roleId);

    /**
     * 根据角色ID和权限ID添加角色权限
     *
     * @param roleId
     * @param id
     * @return
     */
    Boolean addPermissionByIdAndRoleId(Integer roleId, Integer id);

    /**
     * 根据角色ID和权限ID删除角色权限
     *
     * @param roleId
     * @param id
     * @return
     */
    Boolean deletePermissionByIdAndRoleId(Integer roleId, Integer id);

    /**
     * 是否角色已经具备权限
     *
     * @param roleId
     * @param id
     * @return
     */
    Boolean isPermissionByIdAndRoleId(Integer roleId, Integer id);
}
