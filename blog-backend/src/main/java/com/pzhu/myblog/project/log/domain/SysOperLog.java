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
 * 操作日志表
 *
 * @Author Guo Huaijian
 * @Date 2019/2/22 13:22
 */
@Data
@TableName(value = "sys_oper_log")
public class SysOperLog extends BaseEntity {
    /**
     * 日志主键
     */
    @TableId(value = "oper_id", type = IdType.AUTO)
    private Long operId;

    /**
     * 模块标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField(value = "business_type")
    private Integer businessType;

    /**
     * 方法名称
     */
    @TableField(value = "method")
    private String method;

    /**
     * 请求方式
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField(value = "operator_type")
    private Integer operatorType;

    /**
     * 操作人员
     */
    @TableField(value = "oper_name")
    private String operName;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 请求URL
     */
    @TableField(value = "oper_url")
    private String operUrl;

    /**
     * 主机地址
     */
    @TableField(value = "oper_ip")
    private String operIp;

    /**
     * 操作地点
     */
    @TableField(value = "oper_location")
    private String operLocation;

    /**
     * 请求参数
     */
    @TableField(value = "oper_param")
    private String operParam;

    /**
     * 返回参数
     */
    @TableField(value = "json_result")
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 错误消息
     */
    @TableField(value = "error_msg")
    private String errorMsg;

    /**
     * 操作时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "oper_time")
    private Date operTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_OPER_ID = "oper_id";

    public static final String COL_TITLE = "title";

    public static final String COL_BUSINESS_TYPE = "business_type";

    public static final String COL_METHOD = "method";

    public static final String COL_REQUEST_METHOD = "request_method";

    public static final String COL_OPERATOR_TYPE = "operator_type";

    public static final String COL_OPER_NAME = "oper_name";

    public static final String COL_DEPT_NAME = "dept_name";

    public static final String COL_OPER_URL = "oper_url";

    public static final String COL_OPER_IP = "oper_ip";

    public static final String COL_OPER_LOCATION = "oper_location";

    public static final String COL_OPER_PARAM = "oper_param";

    public static final String COL_JSON_RESULT = "json_result";

    public static final String COL_STATUS = "status";

    public static final String COL_ERROR_MSG = "error_msg";

    public static final String COL_OPER_TIME = "oper_time";
}
