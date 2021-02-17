package com.dfrz.javaprojectstage3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Petition;

/**
 * @author：ChenXin
 * @date 2021/2/17 1:59
 */
public interface IPetitionService {
    /**
     * 获取信访件列表(分页)
     * @param page
     * @return
     */
    IPage<Petition> getPetitionPage(Page page);

    /**
     * 添加信访件
     * @param petition
     * @return
     */
    Boolean addPetition(Petition petition);

    /**
     * 根据信访件ID获取信访件
     * @param id
     * @return
     */
    Petition getPetitionById(Integer id);
}
