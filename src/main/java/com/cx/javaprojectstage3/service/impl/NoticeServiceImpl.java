package com.cx.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.Notice;
import com.cx.javaprojectstage3.mapper.NoticeMapper;
import com.cx.javaprojectstage3.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sora
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
        return noticeMapper.selectList(null);
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
    public int addNotice(Notice notice) {
        return noticeMapper.insert(notice);
    }

    @Override
    public int updateNoticeById(Notice notice) {
        return noticeMapper.updateById(notice);
    }

    @Override
    public int deleteNoticeById(Integer id) {
        return noticeMapper.deleteById(id);
    }
}
