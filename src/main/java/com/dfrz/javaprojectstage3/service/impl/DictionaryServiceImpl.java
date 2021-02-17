package com.dfrz.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dfrz.javaprojectstage3.bean.Dictionary;
import com.dfrz.javaprojectstage3.bean.DictionaryData;
import com.dfrz.javaprojectstage3.mapper.DictionaryDataMapper;
import com.dfrz.javaprojectstage3.mapper.DictionaryMapper;
import com.dfrz.javaprojectstage3.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sora
 * @date 2021/2/17 16:35
 */
@Service
public class DictionaryServiceImpl implements IDictionaryService {
    @Autowired
    DictionaryMapper dictionaryMapper;
    @Autowired
    DictionaryDataMapper dictionaryDataMapper;

    @Override
    public Dictionary getDictionaryByTypeKey(String typeKey) {
        // 查询字典获取
        QueryWrapper<Dictionary> dictionaryWrapper = new QueryWrapper<>();
        dictionaryWrapper.eq("type_key", typeKey);
        Dictionary dictionary = dictionaryMapper.selectOne(dictionaryWrapper);

        // 根据字典ID获取资料表内容
        QueryWrapper<DictionaryData> dictionaryDataWrapper = new QueryWrapper<>();
        dictionaryDataWrapper.eq("dictionary_id", dictionary.getId());
        List<DictionaryData> dictionaryDataList = dictionaryDataMapper.selectList(dictionaryDataWrapper);

        dictionary.setDictionaryDataList(dictionaryDataList);
        return dictionary;
    }
}
