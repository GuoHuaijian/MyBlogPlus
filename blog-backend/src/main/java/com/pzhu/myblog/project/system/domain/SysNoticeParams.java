package com.pzhu.myblog.project.system.domain;

import lombok.Data;

/**
 * 通知请求参数
 * @Author Guo Huaijian
 * @Date 2020/2/24 23:35
 */
@Data
public class SysNoticeParams {
    /**
     * 当前页
     */
    private int pageNum;

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     *日志标题
     */
    private String noticeTitle;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 状态
     */
    private String status;
}
