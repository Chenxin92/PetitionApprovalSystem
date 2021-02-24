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
import org.apache.shiro.subject.Subject;
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
 * 作者：wenqi
 * 日期: 2021/2/18 15:59
 * 描述: 领导审批
 */

@RestController
@RequestMapping("/examine1")
public class ExamineController {

    @Autowired
    IPetitionService petitionService;
    @Autowired
    IDictionaryService dictionaryService;
    @Autowired
    ExamineService examineService;
    @Autowired
    IAdviceService adviceService;
    @Autowired
    IUserService userService;
    @Autowired
    IStepService stepService;

    private static Logger logger = LoggerFactory.getLogger(PetitionController.class);

    /**
     * 转至信访件列表页面
     *
     * @return
     */
    @RequestMapping("/toExamineList")
    public ModelAndView toExamineList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("examine_list");
        return mv;
    }

    /**
     * 显示信访件列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getExamineList")
    public Result getExamineList(Integer page, Integer limit, String acceptTime) {
        // 分页
        Page<Petition> petitionPage = new Page<>();
        petitionPage.setCurrent(page);
        petitionPage.setSize(limit);
        ModelAndView mv = new ModelAndView();
        //获取当前登陆用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        IPage<Petition> iPage;

        if (acceptTime==null||acceptTime==""){
            if (user.getRole() == 3) {
                iPage = examineService.getPetitionPagebyCurrentuser(petitionPage, 2, user.getId(), 2);
            } else {
                iPage = examineService.getPetitionPagebyCurrentuser(petitionPage, 3, user.getId(), 3);
            }
        }else {
            if (user.getRole() == 3) {
                iPage = examineService.getPetitionPagebyCurrentuserAndTime(petitionPage, 2, user.getId(), 2,acceptTime);
            } else {
                iPage = examineService.getPetitionPagebyCurrentuserAndTime(petitionPage, 3, user.getId(), 3,acceptTime);
            }
        }


        Result result = ResultUtils.success(iPage.getRecords());
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        return result;
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
     * 转至添加审批意见页面
     *
     * @return
     */
    @RequestMapping("/toPetitionExamine")
    public ModelAndView toPetitionAdd(Integer petitionId) {
        // 获取信源分类
        Dictionary petitionDictionary = dictionaryService.getDictionaryByTypeKey("petition");
        // 获取内容分类
        Dictionary contentDictionary = dictionaryService.getDictionaryByTypeKey("content");
        // 获取部门列表
        List<String> departmentList = userService.getDepartmentList();

        petitionIdall = petitionId;
        //获取当前登陆用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("advice_add");
        mv.addObject("petitionList", petitionDictionary.getDictionaryDataList());
        mv.addObject("contentList", contentDictionary.getDictionaryDataList());
        mv.addObject("departmentList", departmentList);
        mv.addObject("user",user);
        return mv;
    }


    /**
     * 遍历领导
     *
     * @return
     */
    @RequestMapping("/toLeader")
    public List<User> toLeader(String department) {

        //获取当前登陆用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Integer role=user.getRole()+1;
        List<User> leaders= examineService.getUserListByDepartment(department,role);
        return leaders;
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
     * 添加审批
     *
     * @param petitionJSON
     * @return
     */

    Integer petitionIdall;

    @RequestMapping("/addAdvice")
    public Map<String, String> submitPetition(String petitionJSON, Integer petitionId) {

        petitionId = petitionIdall;

        Map<String, String> map = new HashMap<>(1);
        map.put("flag", "false");
        // 获取当前角色
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        // 解析JSON串
        JSONObject jsonObject = JSON.parseObject(petitionJSON);
        // 获取petition参数
        JSONObject petitionParameter = jsonObject.getJSONObject("dataField");
        String department = petitionParameter.getString("department");
        String content = petitionParameter.getString("content");
        String leader=petitionParameter.getString("leader");
        Step step=stepService.getStepByPetitionId(petitionId);

        if (leader==null||leader.equals("")){

            if (user.getRole()==3){
                //二级审批时间
                step.setAuditTime2(new Date(System.currentTimeMillis()));
            }else {
                //三级审批时间
                step.setAuditTime3(new Date(System.currentTimeMillis()));
            }

        }else {
            User leader1=userService.getUserById(Integer.valueOf(leader));


            if (user.getRole()==3){
                step.setExamineUser3(leader1.getId());
                //二级审批时间
                step.setAuditTime2(new Date(System.currentTimeMillis()));
            }else {
                //三级审批时间
                step.setAuditTime3(new Date(System.currentTimeMillis()));
            }
        }




        stepService.updatestepById(step);

        Advice advice = new Advice();
        advice.setContent(content);
        advice.setCreateUser(user.getId());

        // 获取petition附件
        JSONArray arr = jsonObject.getJSONArray("arr");
        List<AttachFile> attachFileList = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            String fileName = arr.getJSONObject(i).getString("fileName");
            Integer type = arr.getJSONObject(i).getInteger("type");
            // 添加petition附件
            AttachFile attachFile = new AttachFile();
            // 信访件属性
            attachFile.setFileType(2);
            attachFile.setFileName(fileName);
            if (type == 1) {
                attachFile.setFilePath("../upload/petition/picture/");
            } else {
                attachFile.setFilePath("../upload/petition/doc/");
            }
            attachFile.setType(type);
            attachFileList.add(attachFile);
            advice.setAttachFileList(attachFileList);
        }

        if (adviceService.addAdvice(advice, petitionId)) {
            map.put("flag", "true");
            Petition petition = petitionService.getPetitionById(petitionId);

            if (leader==null||leader.equals("")){
                petition.setPetitionState(-2);
            }else {
                if (user.getRole() == 2) {
                    petition.setPetitionState(2);
                } else if (user.getRole() == 3){
                    petition.setPetitionState(3);
                }else if (user.getRole()==4){
                    petition.setPetitionState(-2);
                }
            }

            petitionService.updatePetitionById(petition);

            petitionIdall = null;

        }
        return map;
    }

    /**
     * 退回
     * @param petitionJSON
     * @param petitionId
     * @return
     */
    @RequestMapping("/returnAdvice")
    public Map<String, String> returnAdvice(String petitionJSON, Integer petitionId) {

        petitionId = petitionIdall;

        Map<String, String> map = new HashMap<>(1);
        map.put("flag", "false");
        // 获取当前角色
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        // 解析JSON串
        JSONObject jsonObject = JSON.parseObject(petitionJSON);
        // 获取petition参数
        JSONObject petitionParameter = jsonObject.getJSONObject("dataField");
        String department = petitionParameter.getString("department");
        String content = petitionParameter.getString("content");


        Step step=stepService.getStepByPetitionId(petitionId);

        if (user.getRole()==3){
            //二级审批时间
            step.setAuditTime2(new Date(System.currentTimeMillis()));
        }else {
            //三级审批时间
            step.setAuditTime3(new Date(System.currentTimeMillis()));
        }

        stepService.updatestepById(step);

        Advice advice = new Advice();
        advice.setContent(content);
        advice.setCreateUser(user.getId());

        // 获取petition附件
        JSONArray arr = jsonObject.getJSONArray("arr");
        List<AttachFile> attachFileList = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            String fileName = arr.getJSONObject(i).getString("fileName");
            Integer type = arr.getJSONObject(i).getInteger("type");
            // 添加petition附件
            AttachFile attachFile = new AttachFile();
            // 信访件属性
            attachFile.setFileType(2);
            attachFile.setFileName(fileName);
            if (type == 1) {
                attachFile.setFilePath("../upload/petition/picture/");
            } else {
                attachFile.setFilePath("../upload/petition/doc/");
            }
            attachFile.setType(type);
            attachFileList.add(attachFile);
            advice.setAttachFileList(attachFileList);
        }

        if (adviceService.addAdvice(advice, petitionId)) {
            map.put("flag", "true");
            Petition petition = petitionService.getPetitionById(petitionId);
              //退回
                petition.setPetitionState(-1);


            petitionService.updatePetitionById(petition);

            petitionIdall = null;

        }
        return map;
    }

}
