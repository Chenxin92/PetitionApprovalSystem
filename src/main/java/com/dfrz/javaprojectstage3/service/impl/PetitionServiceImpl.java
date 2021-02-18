package com.dfrz.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.AttachFile;
import com.dfrz.javaprojectstage3.bean.Petition;
import com.dfrz.javaprojectstage3.mapper.AttachFileMapper;
import com.dfrz.javaprojectstage3.mapper.PetitionMapper;
import com.dfrz.javaprojectstage3.service.IPetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：ChenXin
 * @date 2021/2/17 2:04
 */
@Service
public class PetitionServiceImpl implements IPetitionService {
    @Autowired
    PetitionMapper petitionMapper;
    @Autowired
    AttachFileMapper attachFileMapper;

    @Override
    public IPage<Petition> getPetitionPage(Page page) {
        return petitionMapper.selectPage(page, null);
    }

    @Override
    public Boolean verificationPetitionByCode(String code) {
        QueryWrapper<Petition> petitionQueryWrapper = new QueryWrapper<>();
        petitionQueryWrapper.eq("code", code);
        Petition petition = petitionMapper.selectOne(petitionQueryWrapper);
        if (petition != null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean addPetition(Petition petition) {
        // 添加petition失败返回
        if (petitionMapper.insert(petition) <= 0) {
            return false;
        }

        // 添加附件
        List<AttachFile> petitionAttachList = petition.getAttachFileList();
        for (AttachFile attachFile : petitionAttachList) {
            // 设置对象ID
            attachFile.setObjectId(petition.getId());
            if (attachFileMapper.insert(attachFile) <= 0) {
                petitionMapper.deleteById(petition.getId());
                return false;
            }
        }
        return true;
    }

    @Override
    public Petition getPetitionById(Integer id) {
        // 获取信访件
        Petition petition =petitionMapper.selectById(id);

        // 获取信访件附件列表
        QueryWrapper<AttachFile> attachFileWrapper = new QueryWrapper<>();
        attachFileWrapper.eq("object_id", id);
        List<AttachFile> attachFileList = attachFileMapper.selectList(attachFileWrapper);
        petition.setAttachFileList(attachFileList);

        return petition;
    }

    @Override
    public Boolean updatePetitionById(Petition petition) {
        // 更新petition失败返回
        if (petitionMapper.updateById(petition) <= 0) {
            return false;
        }

        // 清空已存在附件记录
        QueryWrapper<AttachFile> attachFileQueryWrapper = new QueryWrapper<>();
        attachFileQueryWrapper.eq("object_id", petition.getId());
        attachFileQueryWrapper.eq("file_type", 0);
        List<AttachFile> databaseFileList = attachFileMapper.selectList(attachFileQueryWrapper);
        for (AttachFile attachFile : databaseFileList) {
            attachFileMapper.deleteById(attachFile.getId());
        }
        // 添加附件记录
        List<AttachFile> petitionAttachList = petition.getAttachFileList();
        for (AttachFile attachFile : petitionAttachList) {
            // 设置对象ID
            attachFile.setObjectId(petition.getId());
            // 添加附件失败返回
            if (attachFileMapper.insert(attachFile) <= 0) {
                return false;
            }
        }

        return true;
    }

}
