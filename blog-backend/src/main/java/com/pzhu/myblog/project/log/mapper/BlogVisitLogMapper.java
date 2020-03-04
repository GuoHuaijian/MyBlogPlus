package com.pzhu.myblog.project.log.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.log.domain.BlogVisitLog;

import java.util.List;

/**
 * 访问日志mapper层
 * @author Guo Huaijian
 * @Date 2020/2/22 17:09
 */
public interface BlogVisitLogMapper {

    /**
     * 根据条件获得VisitLog记录
     *
     * @param blogVisitLog 需要查询的条件
     * @return 符合条件的记录
     */
    List<BlogVisitLog> selectBlogVisitLogList(BlogVisitLog blogVisitLog);

    /**
     * 清空访客日志
     */
    void cleanBlogVisitLog();

    /**
     * 根据id清除访客日志
     *
     * @param ids 需要清除的访客日志的id
     * @return 受影响的额行数
     */
    int deleteBlogVisitLogByIds(Integer[] ids);

    /**
     * 根据id获取访客日志
     *
     * @param visitId 需要获取的日志的id
     * @return 访客日志
     */
    BlogVisitLog selectBlogVisitLogById(Integer visitId);

    /**
     * 新增VisitLog记录
     *
     * @param blogVisitLog 需要新增的VisitLog
     * @return 受影响的行数
     */
    int insertBlogVisitLog(BlogVisitLog blogVisitLog);

    /**
     * 根据时间获取当天的访问量
     *
     * @param date
     * @return
     */
    Integer selectVisitCountByCreateTime(String date);

    /**
     * 获取所有数据
     *
     * @return 所有数据的数量
     */
    Integer selectVisitLogTotalCount();

    /**
     * 获取今天的访客数量
     *
     * @return 今天的访客数量
     */
    Integer selectVisitLogTodayCount();

    /**
     * 获取dashboard界面展示的数据
     *
     * @return blogVisitLog集合
     */
    List<BlogVisitLog> selectVisitData();

    /**
     * 分页查询系统访问日志集合
     * @param page 分页对象
     * @return 访问记录集合
     */
    Page<BlogVisitLog> selectList(Page<?> page);
}
