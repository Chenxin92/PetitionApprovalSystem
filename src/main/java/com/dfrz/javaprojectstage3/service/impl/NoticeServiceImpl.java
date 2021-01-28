package com.dfrz.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Notice;
import com.dfrz.javaprojectstage3.mapper.NoticeMapper;
import com.dfrz.javaprojectstage3.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：zhengyefeng
 * 日期: 2021/1/28 15:40
 * 描述:
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;
    @Override
    public IPage<Notice> getNoticeByPage(Page page) {
        return noticeMapper.selectPage(page, null);
    }

    @Override
    public List<Notice> getNotice() {
        return noticeMapper.selectList(null
        );
    }

    @Override
    public void save(Notice notice) {
        noticeMapper.insert(notice);
    }

    @Override
    public Notice getNoticeById(Integer id) {
        return noticeMapper.selectById(id);
    }

    @Override
    public Notice getNoticeBytitle(String title) {
        return null;
    }
}
