package com.dfrz.javaprojectstage3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.*;
import com.dfrz.javaprojectstage3.bean.Dictionary;
import com.dfrz.javaprojectstage3.service.*;
import com.dfrz.javaprojectstage3.utils.Result;
import com.dfrz.javaprojectstage3.utils.ResultUtils;
import com.dfrz.javaprojectstage3.utils.WordToPdfUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * 信访件
 *
 * @author Chenxin
 * @date 2021/02/17 1:09
 */
@RestController
@RequestMapping("/petition")
public class PetitionController {
    @Autowired
    IUserService userService;
    @Autowired
    IPetitionService petitionService;
    @Autowired
    IStepService stepService;
    @Autowired
    IAttachFileService attachFileService;
    @Autowired
    IDictionaryService dictionaryService;

    private static Logger logger = LoggerFactory.getLogger(PetitionController.class);

    /**
     * 转至信访件列表页面
     *
     * @return
     */
    @RequestMapping("/toPetitionList")
    public ModelAndView toPetitionList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("petition_list");
        return mv;
    }

    /**
     * 转至我的信访件列表页面
     *
     * @return
     */
    @RequestMapping("/toMyPetitionList")
    public ModelAndView toMyPetitionList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("petition_MyList");
        return mv;
    }

    /**
     * 显示信访件列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getPetitionList")
    public Result getPetitionList(Integer page, Integer limit, String startTime, String endTime) {
        // 分页
        Page<Petition> petitionPage = getPetitionPage(page, limit);

        // 获取当前登入用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        IPage<Petition> iPage = petitionService.getPetitionListPage(petitionPage, user, startTime, endTime);
        Result result = ResultUtils.success(iPage.getRecords());
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        return result;
    }

    /**
     * 显示我的信访件列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getMyPetitionList")
    public Result getMyPetitionList(Integer page, Integer limit, String startTime, String endTime) {
        // 分页
        Page<Petition> petitionPage = getPetitionPage(page, limit);

        // 获取当前登入用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Petition> petitionList = petitionService.getMyPetitionListPage(petitionPage, user, startTime, endTime);
        Result result = ResultUtils.success(petitionList);
        result.setCode(0);
        result.setCount(petitionList.size());
        return result;
    }

    /**
     * 获取分页
     *
     * @param page
     * @param limit
     * @return
     */
    private Page<Petition> getPetitionPage(Integer page, Integer limit) {
        // 分页
        Page<Petition> petitionPage = new Page<>();
        petitionPage.setCurrent(page);
        petitionPage.setSize(limit);
        return petitionPage;
    }

    /**
     * 转至添加信访件页面
     *
     * @return
     */
    @RequestMapping("/toPetitionAdd")
    public ModelAndView toPetitionAdd() {
        // 获取信源分类
        Dictionary petitionDictionary = dictionaryService.getDictionaryByTypeKey("petition");
        // 获取内容分类
        Dictionary contentDictionary = dictionaryService.getDictionaryByTypeKey("content");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("petition_add");
        mv.addObject("petitionList", petitionDictionary.getDictionaryDataList());
        mv.addObject("contentList", contentDictionary.getDictionaryDataList());
        return mv;
    }

    /**
     * 验证编号是否存在
     *
     * @param code
     * @return
     */
    @RequestMapping("/codeVerification")
    public String codeVerification(String code) {
        if (petitionService.verificationPetitionByCode(code)) {
            return "true";
        }
        return "";
    }

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @RequestMapping("/uploadImage")
    public Result uploadHeadPicture(MultipartFile file) {
        try {
            // 获取服务器class路径
            URL classPath = ResourceUtils.getURL("classpath:");
            logger.info("图片上传路径:" + classPath.getPath());
            // 获取上传文件名
            String fileName = file.getOriginalFilename();
            logger.info("图片名称:" + fileName);
            // 构建服务端上传目录的文件
            File uploadFile = new File(classPath.getPath(), "/static/upload/petition/picture/" + File.separator + fileName);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            // 文件IO读写
            file.transferTo(uploadFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>(1);
        map.put("src", file.getOriginalFilename());
        return ResultUtils.success(map);
    }

    /**
     * DOC上传
     *
     * @param file
     * @return
     */
    @RequestMapping("/uploadDoc")
    public Result uploadDoc(MultipartFile file) {
        try {
            // 获取服务器class路径
            URL classPath = ResourceUtils.getURL("classpath:");
            logger.info("DOC上传路径:" + classPath.getPath());
            // 获取上传文件名
            String fileName = file.getOriginalFilename();
            logger.info("DOC名称:" + fileName);
            // 构建服务端上传目录的文件
            String uploadURL = "/static/upload/petition/doc/" + File.separator;
            File uploadFile = new File(classPath.getPath(), uploadURL + fileName);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            // 文件IO读写
            file.transferTo(uploadFile);

            // 判断文件是否是PDF格式
            int index = fileName.lastIndexOf(".");
            String fileNameEnd = fileName.substring(index + 1);
            if (!"pdf".equalsIgnoreCase(fileNameEnd)) {
                // 获取类加载根目录
                String projectPath = this.getClass().getResource("/").getPath();
                // 原文件路径
                String sourceFile = projectPath + uploadURL + fileName;
                logger.info("源文件路径:" + sourceFile);
                // 目标文件路径
                String destFile = projectPath + uploadURL + fileName.substring(0, fileName.lastIndexOf(".")) + ".PDF";
                logger.info("目标文件路径:" + destFile);
                int flag = WordToPdfUtils.office2PDF(sourceFile, destFile);
                logger.info("转换PDF:" + (flag == 0 ? "转换成功" : "转换失败"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>(1);
        map.put("src", file.getOriginalFilename());
        return ResultUtils.success(map);
    }

    /**
     * 添加/保存信访件
     *
     * @param petitionJSON
     * @return
     */
    @RequestMapping("/addPetition")
    public Map<String, String> submitPetition(String petitionJSON, Integer state) {
        Map<String, String> map = new HashMap<>(1);
        map.put("flag", "false");
        // 获取当前角色
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        // 解析JSON串
        JSONObject jsonObject = JSON.parseObject(petitionJSON);
        // 获取petition参数
        JSONObject petitionParameter = jsonObject.getJSONObject("dataField");
        String code = petitionParameter.getString("code");
        Date acceptTime = petitionParameter.getDate("acceptTime");
        String reflectName = petitionParameter.getString("reflectName");
        String bereflectName = petitionParameter.getString("bereflectName");
        String bereflectPost = petitionParameter.getString("bereflectPost");
        String bereflectDepartment = petitionParameter.getString("bereflectDepartment");
        String content = petitionParameter.getString("content");

        Petition petition = new Petition();
        petition.setCode(code);
        petition.setAcceptTime(acceptTime);
        petition.setReflectName(reflectName);
        petition.setBereflectName(bereflectName);
        petition.setBereflectPost(bereflectPost);
        petition.setBereflectDepartment(bereflectDepartment);
        petition.setContentType(0);
        petition.setPetitionType(0);
        petition.setContent(content);
        // 添加后为一级审批中(经办人),保存为草稿件
        petition.setPetitionState(state);
        petition.setUserId(user.getId());

        // 获取petition附件
        JSONArray arr = jsonObject.getJSONArray("arr");
        List<AttachFile> attachFileList = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            String fileName = arr.getJSONObject(i).getString("fileName");
            Integer type = arr.getJSONObject(i).getInteger("type");
            // 添加petition附件
            AttachFile attachFile = new AttachFile();
            // 信访件属性
            attachFile.setFileType(0);
            attachFile.setFileName(fileName);
            if (type == 1) {
                attachFile.setFilePath("../upload/petition/picture/");
            } else {
                attachFile.setFilePath("../upload/petition/doc/");
            }
            attachFile.setType(type);
            attachFileList.add(attachFile);
            petition.setAttachFileList(attachFileList);
        }

        if (petitionService.addPetition(petition)) {
            map.put("flag", "true");
        }
        return map;
    }

    /**
     * 转至查看信访件页面
     *
     * @return
     */
    @RequestMapping("/toPetitionView")
    public ModelAndView toPetitionView(Integer petitionId) {
        // 获取信访件
        Petition petition = petitionService.getPetitionById(petitionId);
        // 获取信源值
        String petitionType = dictionaryService.getDictionaryDataByDictionaryKeyAndType("petition", petition.getPetitionType()).getDictionaryContent();
        // 获取内容值
        String contentType = dictionaryService.getDictionaryDataByDictionaryKeyAndType("content", petition.getContentType()).getDictionaryContent();
        Step step = stepService.getStepByPetitionId(petitionId);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("petition_view");
        mv.addObject(petition);
        mv.addObject("petitionValue", petitionType);
        mv.addObject("contentValue", contentType);
        mv.addObject(step);
        return mv;
    }

    /**
     * 转至信访件编辑页面
     *
     * @param petitionId
     * @return
     */
    @RequestMapping("/toPetitionEdit")
    public ModelAndView toPetitionEdit(Integer petitionId) {
        // 获取信源分类
        Dictionary petitionDictionary = dictionaryService.getDictionaryByTypeKey("petition");
        // 获取内容分类
        Dictionary contentDictionary = dictionaryService.getDictionaryByTypeKey("content");
        // 获取信访件
        Petition petition = petitionService.getPetitionById(petitionId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("petition_edit");
        mv.addObject(petition);
        mv.addObject("petitionList", petitionDictionary.getDictionaryDataList());
        mv.addObject("contentList", contentDictionary.getDictionaryDataList());
        return mv;
    }

    /**
     * 编辑页面点击删除图片
     *
     * @param id
     * @return
     */
    @RequestMapping("/editClickDeletePicture")
    public String editClickDeletePicture(String id) {
        // 节选出附件ID
        Integer attachFileId = Integer.valueOf(id.substring(1));

        // 删除数据库中附件记录
        if (attachFileService.deleteAttachFileById(attachFileId)) {
            return "true";
        }

        return "false";
    }

    /**
     * 编辑后保存信访件
     *
     * @param petitionJSON
     * @param deletePictureArrayJSON 已删除图片ID数组
     * @return
     */
    @RequestMapping("/editSavePetition")
    public Map<String, String> editSavePetition(String petitionJSON, String deletePictureArrayJSON) {
        Map<String, String> map = new HashMap<>(1);
        map.put("flag", "false");
        // 获取当前角色
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        logger.info("删除: " + deletePictureArrayJSON);

        // 解析删除附件数组JSON串
        JSONArray jsonDeleteArray = JSON.parseArray(deletePictureArrayJSON);
        // 根据数组中的ID删除附件
        for (Object object : jsonDeleteArray) {
            // 获取附件ID
            String idStr = ((String) object).substring(1);
            Integer attachFileId = Integer.valueOf(idStr);
            attachFileService.deleteAttachFileById(attachFileId);
        }

        // 解析JSON串
        JSONObject jsonObject = JSON.parseObject(petitionJSON);
        // 获取petition参数
        JSONObject petitionParameter = jsonObject.getJSONObject("dataField");
        Integer id = petitionParameter.getInteger("id");
        String code = petitionParameter.getString("code");
        Date acceptTime = petitionParameter.getDate("acceptTime");
        String reflectName = petitionParameter.getString("reflectName");
        String bereflectName = petitionParameter.getString("bereflectName");
        String bereflectPost = petitionParameter.getString("bereflectPost");
        String bereflectDepartment = petitionParameter.getString("bereflectDepartment");
        String content = petitionParameter.getString("content");

        Petition petition = new Petition();
        petition.setId(id);
        petition.setCode(code);
        petition.setAcceptTime(acceptTime);
        petition.setReflectName(reflectName);
        petition.setBereflectName(bereflectName);
        petition.setBereflectPost(bereflectPost);
        petition.setBereflectDepartment(bereflectDepartment);
        petition.setContent(content);
        petition.setPetitionState(1);
        petition.setUserId(user.getId());

        // 获取petition附件
        JSONArray arr = jsonObject.getJSONArray("arr");
        List<AttachFile> attachFileList = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            String fileName = arr.getJSONObject(i).getString("fileName");
            Integer type = arr.getJSONObject(i).getInteger("type");
            // 添加petition附件
            AttachFile attachFile = new AttachFile();
            // 信访件属性
            attachFile.setFileType(0);
            attachFile.setFileName(fileName);
            if (type == 1) {
                attachFile.setFilePath("../upload/petition/picture/");
            } else {
                attachFile.setFilePath("../upload/petition/doc/");
            }
            attachFile.setType(type);
            attachFileList.add(attachFile);
            petition.setAttachFileList(attachFileList);
        }

        if (petitionService.updatePetitionById(petition)) {
            map.put("flag", "true");
        }
        return map;
    }

    /**
     * 根据信访件ID删除对应记录
     *
     * @param id
     * @return
     */
    @RequestMapping("deletePetition")
    public String deletePetition(Integer id) {
        if (petitionService.deletePetitionById(id)) {
            return "true";
        }
        return "false";
    }

    /**
     * 转至信访件提交页面
     *
     * @param petitionId
     * @return
     */
    @RequestMapping("/toPetitionSubmit")
    public ModelAndView toPetitionSubmit(Integer petitionId) {
        // 获取信源分类
        Dictionary petitionDictionary = dictionaryService.getDictionaryByTypeKey("petition");
        // 获取内容分类
        Dictionary contentDictionary = dictionaryService.getDictionaryByTypeKey("content");
        // 获取部门列表
        List<String> departmentList = userService.getDepartmentList();
        // 获取信访件
        Petition petition = petitionService.getPetitionById(petitionId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("petition_submit");
        mv.addObject(petition);
        mv.addObject("petitionList", petitionDictionary.getDictionaryDataList());
        mv.addObject("contentList", contentDictionary.getDictionaryDataList());
        mv.addObject("departmentList", departmentList);
        return mv;
    }

    /**
     * 根据部门获取二级审批人员列表
     *
     * @param department
     * @return
     */
    @RequestMapping("/getTwoPrincipalListByDepartment")
    public List<User> getTwoPrincipalListByDepartment(String department) {
        logger.info(department);
        List<User> principalList = userService.getTwoPrincipalListByDepartment(department);
        return principalList;
    }

    /**
     * 经办人提交信访件
     *
     * @param petitionJSON
     * @return
     */
    @RequestMapping("/agentSubmitPetition")
    public Map<String, String> agentSubmitPetition(String petitionJSON) {
        Map<String, String> map = new HashMap<>(1);
        map.put("flag", "false");
        // 获取当前角色
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        // 解析JSON串
        JSONObject jsonObject = JSON.parseObject(petitionJSON);
        // 获取petition参数
        JSONObject petitionParameter = jsonObject.getJSONObject("dataField");

        Integer petitionId = petitionParameter.getInteger("id");
        Integer contentType = petitionParameter.getInteger("contentType");
        Integer petitionType = petitionParameter.getInteger("petitionType");
        Integer principalId = petitionParameter.getInteger("principal");

        Petition petition = new Petition();
        // 信访件ID
        petition.setId(petitionId);
        // 内容分类
        petition.setContentType(contentType);
        // 信源分类
        petition.setPetitionType(petitionType);
        // 提交给经理(二级审批人员)
        petition.setPetitionState(2);
        if (!petitionService.updatePetitionById(petition)) {
            return map;
        }

        Step step = new Step();
        // 一级审批时间
        step.setAuditTime1(new Date(System.currentTimeMillis()));
        // 设置信访件ID
        step.setPetitionId(petitionId);
        // 一级审批人
        step.setExamineUser1(user.getId());
        // 设置二级审批人
        step.setExamineUser2(principalId);
        if (!stepService.addStep(step)) {
            return map;
        }

        map.put("flag", "true");

        return map;
    }

    /**
     * 获取审批人员用户信息
     *
     * @param usersJSON
     * @return
     */
    @RequestMapping("/getStepUsers")
    public JSONArray getStepUsers(String usersJSON) {
        // 解析JSON串
        JSONObject users = JSON.parseObject(usersJSON);

        Integer userId1 = users.getInteger("uId1");
        Integer userId2 = users.getInteger("uId2");
        Integer userId3 = users.getInteger("uId3");

        User user1 = userService.getUserById(userId1);
        User user2 = userService.getUserById(userId2);
        User user3 = userService.getUserById(userId3);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(user1);
        jsonArray.add(user2);
        jsonArray.add(user3);

        return jsonArray;
    }
}
