package com.pzhu.myblog.project.frontend.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 归档信息
 * @Author Guo Huaijian
 * @Date 2020/3/11 13:04
 */
@Data
public class Archive implements Serializable {

    /**
     * 文章id
     */
    private int id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 归档年份
     */
    private String year;

    /**
     * 归档月份
     */
    private String month;
}
