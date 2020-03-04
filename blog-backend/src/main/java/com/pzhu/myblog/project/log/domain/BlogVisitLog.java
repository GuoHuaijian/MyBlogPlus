package com.pzhu.myblog.project.log.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pzhu.myblog.framework.web.domain.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 访问日志表
 * @Author Guo Huaijian
 * @Date 2020/2/22 17:04
 */
@Data
@TableName(value = "blog_visit_log")
public class BlogVisitLog extends BaseEntity {
    @TableId(value = "visit_id", type = IdType.AUTO)
    private Integer visitId;

    /**
     * ip地址
     */
    @TableField(value = "ip_addr")
    private String ipAddr;

    /**
     * 访问地址
     */
    @TableField(value = "location")
    private String location;

    /**
     * 浏览器类型
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    private String os;

    /**
     * 爬虫
     */
    @TableField(value = "spider")
    private String spider;

    /**
     * 请求地址
     */
    @TableField(value = "request_url")
    private String requestUrl;

    /**
     * 访问错误的提示信息
     */
    @TableField(value = "error_msg")
    private String errorMsg;

    /**
     * 请求的模块
     */
    @TableField(value = "title")
    private String title;

    @TableField(value = "create_by")
    private String createBy;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 访问状态,0表示正常，1表示不正常
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 入口地址
     */
    private String entryUrl;

    private static final long serialVersionUID = 1L;

    public static final String COL_VISIT_ID = "visit_id";

    public static final String COL_IP_ADDR = "ip_addr";

    public static final String COL_LOCATION = "location";

    public static final String COL_BROWSER = "browser";

    public static final String COL_OS = "os";

    public static final String COL_SPIDER = "spider";

    public static final String COL_REQUEST_URL = "request_url";

    public static final String COL_ERROR_MSG = "error_msg";

    public static final String COL_TITLE = "title";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_STATUS = "status";
}
