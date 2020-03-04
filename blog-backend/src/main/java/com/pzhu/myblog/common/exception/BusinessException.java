package com.pzhu.myblog.common.exception;

import com.pzhu.myblog.framework.aspectj.lang.enums.BusinessStatus1;

/**
 * 全局业务异常
 * @Author Guo Huaijian
 * @Date 2020/2/12 19:50
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 3034121940056795549L;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException() {

    }

    public BusinessException(BusinessStatus1 status) {
        super(status.getMessage());
        this.code = status.getCode();
    }
}
