package com.cx.javaprojectstage3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.Petition;
import com.cx.javaprojectstage3.bean.Step;
import com.cx.javaprojectstage3.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StepMapper extends BaseMapper<Step> {

    IPage<Petition> getPetitionPagebyCurrentuserAndTime(Page page, @Param("examineuser") Integer examineuser, @Param("userid") Integer userid, @Param("petitionstate") Integer petitionstate,@Param("acceptTime")String acceptTime);


    IPage<Petition> getPetitionPagebyCurrentuser(Page page, @Param("examineuser") Integer examineuser, @Param("userid") Integer userid, @Param("petitionstate") Integer petitionstate);




    /**
     * 根据部门获取用户列表
     *
     * @return
     */
    List<User> getUserListByDepartment(@Param("department")String department,@Param("role")Integer role);

}