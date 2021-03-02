package com.cx.javaprojectstage3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.Notice;

import java.util.List;

/**
 * @author Sora
 */
public interface NoticeService {
    /**
     * 获取所有公告(分页)
     *
     * @param page
     * @return
     */
    IPage<Notice> getNoticeByPage(Page<?> page);

    /**
     * 获取所有公告
     *
     * @return
     */
    List<Notice> getNotice();

    void save(Notice notice);

    /**
     * 添加公告
     *
     * @param notice
     * @return
     */
    int addNotice(Notice notice);

    /**
     * 更新公告
     *
     * @param notice
     * @return
     */
    int updateNoticeById(Notice notice);

    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    int deleteNoticeById(Integer id);

    /**
     * 根据ID获取公告
     *
     * @param id
     * @return
     */
    Notice getNoticeById(Integer id);
}
