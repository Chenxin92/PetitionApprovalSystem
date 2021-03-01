package com.cx.javaprojectstage3.service.impl;

import com.cx.javaprojectstage3.bean.AttachFile;
import com.cx.javaprojectstage3.mapper.AttachFileMapper;
import com.cx.javaprojectstage3.service.IAttachFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：ChenXin
 * @date 2021/2/19 13:37
 */
@Service
public class AttachFileServiceImpl implements IAttachFileService {
    @Autowired
    AttachFileMapper attachFileMapper;

    @Override
    public Boolean addAttachFile(AttachFile attachFile) {
        return attachFileMapper.insert(attachFile) > 0;
    }

    @Override
    public Boolean deleteAttachFileById(Integer id) {
        return attachFileMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean updateAttachFileById(AttachFile attachFile) {
        return attachFileMapper.updateById(attachFile) >0;
    }

    @Override
    public AttachFile getAttachFileById(Integer id) {
        return attachFileMapper.selectById(id);
    }
}
