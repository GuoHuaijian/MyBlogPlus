package com.pzhu.myblog.project.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 文章全部信息
 *
 * @Author Guo Huaijian
 * @Date 2020/2/18 15:37
 */
@Data
public class ArticleDetail {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 文章分类
     */
    private String category;

    /**
     * 文章标签
     */
    private List<String> tag;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 简介
     */
    private String summary;

    /**
     * 正文
     */
    private String content;

    /**
     * 作者
     */
    private String author;

    /**
     * 作者头像
     */
    private String authorImg;

    /**
     * 封面
     */
    private String summaryImg;

    /**
     * 文章状态
     */
    private String status;

    /**
     * 是否推荐
     */
    private String support;

    /**
     * 点击量
     */
    private Integer clickNum;

    /**
     * 创建时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
