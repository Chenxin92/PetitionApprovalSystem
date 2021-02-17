package com.dfrz.javaprojectstage3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dfrz.javaprojectstage3.bean.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Sora
 */
@Mapper
@Repository
public interface DictionaryMapper extends BaseMapper<Dictionary> {

}