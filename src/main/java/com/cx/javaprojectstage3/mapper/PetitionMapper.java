package com.cx.javaprojectstage3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.javaprojectstage3.bean.Petition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PetitionMapper extends BaseMapper<Petition> {

}