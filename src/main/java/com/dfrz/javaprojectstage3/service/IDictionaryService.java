package com.dfrz.javaprojectstage3.service;

import com.dfrz.javaprojectstage3.bean.Dictionary;
import com.dfrz.javaprojectstage3.bean.DictionaryData;

/**
 * 字典
 * @author：ChenXin
 * @date 2021/2/17 16:28
 */
public interface IDictionaryService {
    /**
     * 根据关键字获取字典
     * @param typeKey
     * @return
     */
    Dictionary getDictionaryByTypeKey(String typeKey);

    /**
     * 根据字典KEY和数据类型获取字典数据
     *
     * @param key
     * @param type
     * @return
     */
    DictionaryData getDictionaryDataByDictionaryKeyAndType(String key, Integer type);
}
