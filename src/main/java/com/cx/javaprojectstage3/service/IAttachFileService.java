package com.cx.javaprojectstage3.service;

import com.cx.javaprojectstage3.bean.AttachFile;

/**
 * @author：ChenXin
 * @date 2021/2/19 13:24
 */
public interface IAttachFileService {
    /**
     * 添加附件
     *
     * @param attachFile
     * @return true 添加成功
     */
    Boolean addAttachFile(AttachFile attachFile);

    /**
     * 根据附件ID删除附件
     *
     * @param id
     * @return true 删除成功
     */
    Boolean deleteAttachFileById(Integer id);

    /**
     * 更新附件
     *
     * @param attachFile
     * @return true 更新成功
     */
    Boolean updateAttachFileById(AttachFile attachFile);

    /**
     * 根据附件ID获取附件
     *
     * @param id
     * @return AttachFile 查到对象
     */
    AttachFile getAttachFileById(Integer id);
}
