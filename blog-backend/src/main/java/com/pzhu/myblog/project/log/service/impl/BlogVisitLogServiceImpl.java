package com.pzhu.myblog.project.log.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.log.domain.BlogVisitLog;
import com.pzhu.myblog.project.log.mapper.BlogVisitLogMapper;
import com.pzhu.myblog.project.log.service.BlogVisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 访问日志实现层
 * @Author Guo Huaijian
 * @Date 2020/2/22 17:27
 */
@Service
public class BlogVisitLogServiceImpl implements BlogVisitLogService {

    @Autowired
    BlogVisitLogMapper blogVisitLogMapper;

    /**
     *根据条件获取访客日志
     * @param blogVisitLog 带有条件的访客日志信息
     * @return
     */
    @Override
    public List<BlogVisitLog> selectVisitLogList(BlogVisitLog blogVisitLog) {
        return blogVisitLogMapper.selectBlogVisitLogList(blogVisitLog);
    }

    /**
     * 根据id批量删除日志
     * @param ids 需要删除的日志的id
     * @return
     */
    @Override
    public int deleteBlogVisitLogByIds(Integer[] ids) {
        return blogVisitLogMapper.deleteBlogVisitLogByIds(ids);
    }

    /**
     * 根据id获取单个VisitLog记录
     * @param visitId 需要获得Log的id
     * @return
     */
    @Override
    public BlogVisitLog selectBlogVisitLogById(Integer visitId) {
        return blogVisitLogMapper.selectBlogVisitLogById(visitId);
    }

    /**
     * 清空访问日志
     */
    @Override
    public void cleanBlogVisitLog() {
        blogVisitLogMapper.cleanBlogVisitLog();
    }

    /**
     * 新增访问记录
     * @param blogVisitLog
     */
    @Override
    public void insertBlogVisitLog(BlogVisitLog blogVisitLog) {
        System.out.println(blogVisitLog.toString());
        blogVisitLogMapper.insertBlogVisitLog(blogVisitLog);
    }

    /**
     * 获取历史访客总数
     * @return 访客数量
     */
    @Override
    public Integer selectVisitLogTotalCount() {
        return blogVisitLogMapper.selectVisitLogTotalCount();
    }

    /**
     * 获取今天访客记录
     * @return 访客数量
     */
    @Override
    public Integer selectVisitLogTodayCount() {
        return blogVisitLogMapper.selectVisitLogTodayCount();
    }

    /**
     * 分页查询系统访问日志集合
     * @param page 分页对象
     * @return 访问记录集合
     */
    @Override
    public Page<BlogVisitLog> selectList(Page<?> page) {
        return blogVisitLogMapper.selectList(page);
    }

}
