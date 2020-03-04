package com.pzhu.myblog.project.frontend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 前台文章列表
 * @Author Guo Huaijian
 * @Date 2020/2/28 15:46
 */
@Data
public class Article {

    /**
     * 文章id
     */
    private int id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简介
     */
    private String summary;

    /**
     * 文章封面
     */
    private String summaryImg;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 作者头像
     */
    private String authorImg;

    /**
     * 分类
     */
    private String category;

    /**
     * 点击量
     */
    private int clickNum;

    /**
     * 创建时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
