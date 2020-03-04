package com.pzhu.myblog.common.dto;

import com.pzhu.myblog.common.constant.HttpStatus;
import com.pzhu.myblog.common.utils.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 *自定义返回结果
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28 18:22
 */
public class BaseResult extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 BaseResult 对象，使其表示一个空消息。
     */
    public BaseResult() {
    }

    /**
     * 初始化一个新创建的 BaseResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public BaseResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 BaseResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public BaseResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static BaseResult success() {
        return BaseResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static BaseResult success(Object data) {
        return BaseResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static BaseResult success(String msg) {
        return BaseResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static BaseResult success(String msg, Object data) {
        return new BaseResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param code 状态码
     * @return 成功消息
     */
    public static BaseResult success(String msg, int code) {
        return new BaseResult(code, msg);
    }

    /**
     *返回成功消息
     *
     * @param code 状态码
     * @param data 数据对象
     * @param msg 返回内容
     * @return
     */
    public static BaseResult success(int code, Object data, String msg) {
        return new BaseResult(code, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static BaseResult error() {
        return BaseResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static BaseResult error(String msg) {
        return BaseResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static BaseResult error(String msg, Object data) {
        return new BaseResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static BaseResult error(int code, String msg) {
        return new BaseResult(code, msg, null);
    }

}
