package com.dfrz.javaprojectstage3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dfrz.javaprojectstage3.bean.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getPermissionsByRoleId(@Param("role_id") Integer role_id);

    Integer getRolePermissionCount(@Param("role_id")Integer role_id, @Param("permission_id")Integer permission_id);

}