package com.cx.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.AttachFile;
import com.cx.javaprojectstage3.bean.Petition;
import com.cx.javaprojectstage3.bean.Step;
import com.cx.javaprojectstage3.bean.User;
import com.cx.javaprojectstage3.mapper.AttachFileMapper;
import com.cx.javaprojectstage3.mapper.PetitionMapper;
import com.cx.javaprojectstage3.mapper.StepMapper;
import com.cx.javaprojectstage3.service.IPetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    StepMapper stepMapper;

    @Override
    public IPage<Petition> getPetitionListPage(Page page, User user, String startTime, String endTime) {
        Integer roleId = user.getRole();
        QueryWrapper<Petition> petitionQueryWrapper = new QueryWrapper<>();

        // 起始时间不为空,添加大于等于起始时间的收件时间条件
        if (startTime != null && !("".equals(startTime))) {
            petitionQueryWrapper.ge("accept_time", startTime);
        }

        // 结束时间不为空,添加小于等于结束时间的收件时间条件
        if (endTime != null && !("".equals(endTime))) {
            petitionQueryWrapper.le("accept_time", endTime);
        }

        // 用户
        if (roleId == 1) {
            // 只显示个人信访件
            petitionQueryWrapper.eq("user_id", user.getId());
        }
        // 经办人
        else if (roleId == 2) {
            // 除草稿件均可见
            petitionQueryWrapper.ne("petition_state", 0);

        }
        return petitionMapper.selectPage(page, petitionQueryWrapper);
    }

    @Override
    public List<Petition> getMyPetitionListPage(Page page, User user, String startTime, String endTime) {
        // 当前用户角色ID
        Integer roleId = user.getRole();

        QueryWrapper<Petition> petitionQueryWrapper = new QueryWrapper<>();
        List<Petition> petitionList = new ArrayList<>();

        // 起始时间不为空,添加大于等于起始时间的收件时间条件
        if (startTime != null && !("".equals(startTime))) {

        }

        // 结束时间不为空,添加小于等于结束时间的收件时间条件
        if (endTime != null && !("".equals(endTime))) {

        }

        // 用户
        if (roleId == 1) {
            // 只显示个人信访件
            petitionQueryWrapper.eq("user_id", user.getId());
            petitionList = petitionMapper.selectList(petitionQueryWrapper);
        }

        // 经办人/经理/领导个人列表
        else if (roleId > 1) {
            // 获取当前用户再step中相关的信访件
            QueryWrapper<Step> stepQueryWrapper = new QueryWrapper<>();
            if (roleId == 2) {
                stepQueryWrapper.eq("examine_user1", user.getId());
            } else if (roleId == 3) {
                stepQueryWrapper.eq("examine_user2", user.getId());
            } else if (roleId == 4) {
                stepQueryWrapper.eq("examine_user3", user.getId());
            }
            List<Step> stepList = stepMapper.selectList(stepQueryWrapper);

            // 查出所有信访件
            List<Petition> petitionListTemp = petitionMapper.selectList(null);
            // 获取当前用户提交/审批信访件列表
            for (Petition petition : petitionListTemp) {
                for (Step step : stepList) {
                    if (petition.getId().equals(step.getPetitionId())) {
                        petitionList.add(petition);
                    }
                }
            }
        }

        // 分页功能
        // 每页记录数
        Integer pageSize = (int) (page.getSize());
        //每页的起始索引
        Integer pageNo = (int) ((page.getCurrent() - 1) * pageSize);
        //记录总数
        Integer sum = petitionList.size();

        if (pageNo + pageSize > sum) {
            petitionList = petitionList.subList(pageNo, sum);
        } else {
            petitionList = petitionList.subList(pageNo, pageNo + pageSize);
        }

        return petitionList;
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
        // 无附件直接返回
        if (petitionAttachList == null) {
            return true;
        }
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
        Petition petition = petitionMapper.selectById(id);

        // 获取信访件附件列表
        QueryWrapper<AttachFile> attachFileWrapper = new QueryWrapper<>();
        attachFileWrapper.eq("object_id", id);
        attachFileWrapper.eq("file_type", 0);
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

        // 添加附件记录
        List<AttachFile> petitionAttachList = petition.getAttachFileList();
        // 无附件返回
        if (petitionAttachList == null) {
            return true;
        }
        for (AttachFile attachFile : petitionAttachList) {
            // 判断附件是否已存在记录
            QueryWrapper<AttachFile> attachFileQueryWrapper = new QueryWrapper<>();
            attachFileQueryWrapper.eq("object_id", petition.getId());
            attachFileQueryWrapper.eq("file_type", 0);
            attachFileQueryWrapper.eq("file_name", attachFile.getFileName());
            attachFileQueryWrapper.eq("file_path", attachFile.getFilePath());
            if (attachFileMapper.selectOne(attachFileQueryWrapper) != null) {
                // 存在返回执行下次循环
                continue;
            }

            // 设置对象ID
            attachFile.setObjectId(petition.getId());
            // 添加附件失败返回
            if (attachFileMapper.insert(attachFile) <= 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Boolean deletePetitionById(Integer id) {
        // 查出信访件相关所有附件
        QueryWrapper<AttachFile> attachFileQueryWrapper = new QueryWrapper<>();
        attachFileQueryWrapper.eq("object_id", id);
        attachFileQueryWrapper.eq("file_type", 0);
        List<AttachFile> attachFileList = attachFileMapper.selectList(attachFileQueryWrapper);
        if (attachFileList != null || attachFileList.size() > 0) {
            // 删除相关信访件附件
            for (AttachFile attachFile : attachFileList) {
                if (attachFileMapper.deleteById(attachFile.getId()) <= 0) {
                    // 删除附件失败
                    return false;
                }
            }
        }

        // 删除信访件
        return petitionMapper.deleteById(id) > 0;
    }
}
