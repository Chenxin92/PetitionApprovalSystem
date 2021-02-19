package com.dfrz.javaprojectstage3.service;

import com.dfrz.javaprojectstage3.bean.AttachFile;

/**
 * @author：ChenXin
 * @date 2021/2/19 13:24
 */
public interface IAttachFileService {
    /**
     * 添加附件
     *
     * @param attachFile
     * @return
     */
    Boolean addAttachFile(AttachFile attachFile);

    /**
     * 根据附件ID删除附件
     *
     * @param id
     * @return
     */
    Boolean deleteAttachFileById(Integer id);


}
