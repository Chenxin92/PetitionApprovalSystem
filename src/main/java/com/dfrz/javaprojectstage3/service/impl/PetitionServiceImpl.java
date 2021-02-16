package com.dfrz.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Petition;
import com.dfrz.javaprojectstage3.mapper.PetitionMapper;
import com.dfrz.javaprojectstage3.service.IPetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：ChenXin
 * @date 2021/2/17 2:04
 */
@Service
public class PetitionServiceImpl implements IPetitionService {
    @Autowired
    PetitionMapper petitionMapper;

    @Override
    public IPage<Petition> getPetitionPage(Page page) {
        return petitionMapper.selectPage(page, null);
    }
}
