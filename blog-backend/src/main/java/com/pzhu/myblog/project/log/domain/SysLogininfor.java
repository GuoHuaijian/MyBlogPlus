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
 * 登录日志表
 *
 * @Author Guo Huaijian
 * @Date 2019/2/22 13:22
 */
@Data
@TableName(value = "sys_logininfor")
public class SysLogininfor extends BaseEntity {
    /**
     * 访问ID
     */
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    /**
     * 用户账号
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 登录IP地址
     */
    @TableField(value = "ipaddr")
    private String ipaddr;

    /**
     * 登录地点
     */
    @TableField(value = "login_location")
    private String loginLocation;

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
     * 登录状态（0成功 1失败）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 提示消息
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 访问时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "login_time")
    private Date loginTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_INFO_ID = "info_id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_IPADDR = "ipaddr";

    public static final String COL_LOGIN_LOCATION = "login_location";

    public static final String COL_BROWSER = "browser";

    public static final String COL_OS = "os";

    public static final String COL_STATUS = "status";

    public static final String COL_MSG = "msg";

    public static final String COL_LOGIN_TIME = "login_time";
}
