package com.dfrz.javaprojectstage3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Petition;
import com.dfrz.javaprojectstage3.bean.Step;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StepMapper extends BaseMapper<Step> {

    public IPage<Petition> getPetitionPagebyCurrentuser(Page page, @Param("examineuser") Integer examineuser, @Param("userid") Integer userid);

}