package com.pzhu.myblog.framework.aspectj.lang.annotation;

import java.lang.annotation.*;

/**
 *访问日志记录注解
 * @author Guo Huaijian
 * @Date 2020/2/22 16:48
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VLog {

    /**
     * 请求的模块
     */
    String title() default "";

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
