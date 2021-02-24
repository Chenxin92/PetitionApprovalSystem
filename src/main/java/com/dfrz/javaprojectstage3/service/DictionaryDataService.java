package com.dfrz.javaprojectstage3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.DictionaryData;

import java.util.List;

/**
 * 作者：zhengyefeng
 * 日期: 2021/2/24 15:26
 * 描述:
 */
public interface DictionaryDataService {
    public IPage<DictionaryData> getDictionaryDataByPage(Page page);
    public List<DictionaryData> getDictionaryData();
    public void save(DictionaryData dictionaryData);
    public int adddictionaryData(DictionaryData dictionaryData);
    public int updateDictionaryDataByid(DictionaryData dictionaryData);
    public int deleteDictionaryDataById(Integer id);
    public DictionaryData getDictionaryDataById(Integer id);
}
