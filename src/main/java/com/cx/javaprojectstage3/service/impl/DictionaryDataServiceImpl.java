package com.cx.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.DictionaryData;
import com.cx.javaprojectstage3.mapper.DictionaryDataMapper;
import com.cx.javaprojectstage3.service.DictionaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：zhengyefeng
 * 日期: 2021/2/24 15:27
 * 描述:
 */
@Service
public class DictionaryDataServiceImpl implements DictionaryDataService {
    @Autowired
    DictionaryDataMapper dictionaryDataMapper;
    @Override
    public IPage<DictionaryData> getDictionaryDataByPage(Page page) {
        return dictionaryDataMapper.selectPage(page, null);
    }

    @Override
    public List<DictionaryData> getDictionaryData() {
        return dictionaryDataMapper.selectList(null
        );
    }

    @Override
    public void save(DictionaryData dictionaryData) {
        dictionaryDataMapper.insert(dictionaryData);
    }

    @Override
    public int adddictionaryData(DictionaryData dictionaryData) {
        return dictionaryDataMapper.insert(dictionaryData);
    }

    @Override
    public int updateDictionaryDataByid(DictionaryData dictionaryData) {
        return dictionaryDataMapper.updateById(dictionaryData);
    }

    @Override
    public int deleteDictionaryDataById(Integer id) {
        return dictionaryDataMapper.deleteById(id);
    }

    @Override
    public DictionaryData getDictionaryDataById(Integer id) {
        return dictionaryDataMapper.selectById(id);
    };
    }

