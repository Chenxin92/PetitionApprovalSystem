package com.dfrz.javaprojectstage3.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.dfrz.javaprojectstage3.bean.Permission;
import com.dfrz.javaprojectstage3.mapper.PermissionMapper;
import com.dfrz.javaprojectstage3.service.IPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    private static Logger logger= LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    PermissionMapper permissionMapper;
    public String jsonTree(Integer roleid) {
        Subject subject = SecurityUtils.getSubject();
        List<Permission> list=permissionMapper.selectList(null);
        Permission root = new Permission(0, "顶层节点", -1,"top");
        Permission node = null;
        for (Permission p:list
                ) {
            node=new Permission(p.getId(),p.gettitle(),p.getPid(),p.getPermissionKey());
            //根据角色找权限
            Integer isPermitted=permissionMapper.getRolePermissionCount(roleid,p.getId());
            if(isPermitted>0){
                node.setChecked(true);
            }
            //递归加入到树形节点中
            root.add(node);
        }


        //转JSON  GSON、fastjson
        String json= JSONObject.toJSONString(root);
        logger.info(json);
        return json;
    }
}
