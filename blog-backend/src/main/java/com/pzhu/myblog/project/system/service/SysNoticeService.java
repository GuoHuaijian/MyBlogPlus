package com.pzhu.myblog.project.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pzhu.myblog.project.system.domain.SysNotice;

import java.util.List;

/**
 * 通知service层
 *
 * @Author Guo Huaijian
 * @Date 2020/2/24 22:09
 */
public interface SysNoticeService extends IService<SysNotice> {

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * @param page 分页对象
     * @return 公告集合
     */
    Page<SysNotice> selectNoticeList(Page<?> page);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    int insertNotice(SysNotice notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    int updateNotice(SysNotice notice);

    /**
     * 删除公告信息
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    int deleteNoticeByIds(List<Long> noticeIds);

}
