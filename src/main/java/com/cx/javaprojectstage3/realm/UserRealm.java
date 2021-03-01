package com.cx.javaprojectstage3.realm;


import com.cx.javaprojectstage3.bean.Permission;
import com.cx.javaprojectstage3.bean.User;
import com.cx.javaprojectstage3.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    IUserService userService;

    /*登陆之后-授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 获取user
        User user = (User) subject.getPrincipal();
        // 设置session超时时长(一周,单位:ms)
        subject.getSession().setTimeout(1000 * 60 * 60 * 24 * 7);
        //根据uname从数据库查询角色和权限
        User userRolePermission = userService.getUserRolePermissionByUname(user.getUsername());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<String> roles = new ArrayList<>();
        roles.add(userRolePermission.getUrole().getRoleKey());
        //Shiro框架中添加角色
        info.addRoles(roles);

        List<String> permissions = new ArrayList<>();
        for (Permission permission : userRolePermission.getUrole().getPermissions()
                ) {
            permissions.add(permission.getPermissionKey());
        }
        //Shiro框架中添加权限
        info.addStringPermissions(permissions);
        return info;
    }

    /*登陆-认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //根据用户名查询用户
        User user = userService.getUserByUname(token.getUsername());
        if (user == null) {
            // 用户不存在时抛出异常
            throw new UnknownAccountException();
        } else {
            user = userService.getUserById(user.getId());
            // 根据ID二次验证
            if (user == null) {
                // 根据用户ID查询用户不存在时,抛出登录认证异常
                throw new AuthenticationException();
            }
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
                credentialsSalt, getName());
        return simpleAuthenticationInfo;
    }


}
