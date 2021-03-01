package com.cx.javaprojectstage3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.Notice;

import java.util.List;

/**
 * 作者：zhengyefeng
 * 日期: 2021/1/28 15:39
 * 描述:
 */
public interface NoticeService {
    public IPage<Notice> getNoticeByPage(Page<?> page);
    public List<Notice> getNotice();
    public void save(Notice notice);
    public int addnotice(Notice notice);
    public int updatenoticeByid(Notice notice);
    public int deletenoticeById(Integer id);
    public Notice getNoticeBytitle(String title);
    public Notice getNoticeById(Integer id);


}
