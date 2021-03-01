package com.cx.javaprojectstage3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.Petition;
import com.cx.javaprojectstage3.bean.User;

import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2021/2/19 13:05
 * 描述:
 */
public interface ExamineService {

    /**
     *
     * 根据当前登录人获取需要登录人审批的信访件（分页）
     *
     * @param page
     * @return
     */
    IPage<Petition> getPetitionPagebyCurrentuserAndTime(Page page,Integer examineuser, Integer userid, Integer petitionstate,String acceptTime);


    /**
     *
     * 根据当前登录人获取需要登录人审批的信访件（分页）
     *
     * @param page
     * @return
     */
    IPage<Petition> getPetitionPagebyCurrentuser(Page page,Integer examineuser, Integer userid, Integer petitionstate);


    /**
     * 根据部门获取用户列表
     *
     * @return
     */
    List<User> getUserListByDepartment(String department, Integer role);

}
