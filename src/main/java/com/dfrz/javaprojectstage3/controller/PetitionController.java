package com.dfrz.javaprojectstage3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.*;
import com.dfrz.javaprojectstage3.bean.Dictionary;
import com.dfrz.javaprojectstage3.mapper.PetitionMapper;
import com.dfrz.javaprojectstage3.service.IDictionaryService;
import com.dfrz.javaprojectstage3.service.IPetitionService;
import com.dfrz.javaprojectstage3.utils.Result;
import com.dfrz.javaprojectstage3.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    IPetitionService petitionService;
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
     * 显示信访件列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getPetitionList")
    public Result getPetitionList(Integer page, Integer limit) {
        // 分页
        Page<Petition> petitionPage = new Page<>();
        petitionPage.setCurrent(page);
        petitionPage.setSize(limit);

        IPage<Petition> iPage = petitionService.getPetitionPage(petitionPage);
        Result result = ResultUtils.success(iPage.getRecords());
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        return result;
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
            File uploadFile = new File(classPath.getPath(), "/static/upload/petition/doc/" + File.separator + fileName);
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
     * 添加信访件
     *
     * @param petitionJSON
     * @return
     */
    @RequestMapping("/addPetition")
    public Map<String, String> submitPetition(String petitionJSON) {
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
        Integer contentType = petitionParameter.getInteger("contentType");
        Integer petitionType = petitionParameter.getInteger("petitionType");
        String content = petitionParameter.getString("content");

        Petition petition = new Petition();
        petition.setCode(code);
        petition.setAcceptTime(acceptTime);
        petition.setReflectName(reflectName);
        petition.setBereflectName(bereflectName);
        petition.setBereflectPost(bereflectPost);
        petition.setBereflectDepartment(bereflectDepartment);
        petition.setContentType(contentType);
        petition.setPetitionType(petitionType);
        petition.setContent(content);
        // 添加后为一级审批(经办人)
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
    @RequestMapping("/toView")
    public ModelAndView toView(Integer petitionId) {
        // 获取信访件
        Petition petition = petitionService.getPetitionById(petitionId);
        // 获取信源值
        Dictionary petitionDictionary = dictionaryService.getDictionaryByTypeKey("petition");
        List<DictionaryData> petitionDictionaryDataList = petitionDictionary.getDictionaryDataList();
        String petitionType = "";
        for (DictionaryData dictionaryData : petitionDictionaryDataList) {
            if (dictionaryData.getValue().equals(petition.getPetitionType())) {
                petitionType = dictionaryData.getDictionaryContent();
                break;
            }
        }

        // 获取内容值
        Dictionary contentDictionary = dictionaryService.getDictionaryByTypeKey("content");
        List<DictionaryData> contentDictionaryDataList = contentDictionary.getDictionaryDataList();
        String contentType = "";
        for (DictionaryData dictionaryData : contentDictionaryDataList) {
            if (dictionaryData.getValue().equals(petition.getPetitionType())) {
                contentType = dictionaryData.getDictionaryContent();
                break;
            }
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("petition_view");
        mv.addObject(petition);
        mv.addObject("petitionValue", petitionType);
        mv.addObject("contentValue", contentType);
        return mv;
    }


    /**
     * 转至信访件编辑页面
     *
     * @param petitionId
     * @return
     */
    @RequestMapping("/toEdit")
    public ModelAndView toEdit(Integer petitionId) {
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
        Integer attachFileId = Integer.valueOf(id.substring(1));
        Petition petition = new Petition();
        AttachFile attachFile = new AttachFile();
        attachFile.setId(attachFileId);
        petition.getAttachFileList().add(attachFile);


        return "false";
    }

    /**
     * 编辑后保存信访件
     *
     * @param petitionJSON
     * @return
     */
    @RequestMapping("/editSavePetition")
    public Map<String, String> editSavePetition(String petitionJSON) {
        Map<String, String> map = new HashMap<>(1);
        map.put("flag", "false");
        // 获取当前角色
        User user = (User) SecurityUtils.getSubject().getPrincipal();

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
        Integer contentType = petitionParameter.getInteger("contentType");
        Integer petitionType = petitionParameter.getInteger("petitionType");
        String content = petitionParameter.getString("content");

        Petition petition = new Petition();
        petition.setId(id);
        petition.setCode(code);
        petition.setAcceptTime(acceptTime);
        petition.setReflectName(reflectName);
        petition.setBereflectName(bereflectName);
        petition.setBereflectPost(bereflectPost);
        petition.setBereflectDepartment(bereflectDepartment);
        petition.setContentType(contentType);
        petition.setPetitionType(petitionType);
        petition.setContent(content);
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
}
