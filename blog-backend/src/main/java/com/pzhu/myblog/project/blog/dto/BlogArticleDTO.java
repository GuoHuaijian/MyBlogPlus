package com.pzhu.myblog.project.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * 前端过来的文章对象
 * @Author Guo Huaijian
 * @Date 2020/2/16 18:23
 */
@Data
public class BlogArticleDTO {

    /**
     * 文章状态
     */
   private String status;

    /**
     * 标题
     */
   private String title;

    /**
     * 分类
     */
   private String category;

    /**
     * 标签
     */
   private List<String> tag;

    /**
     * 作者
     */
   private String author;

    /**
     * 简介
     */
   private String summary;

    /**
     * 封面地址
     */
   private String summary_img;

    /**
     * 正文
     */
   private String content;

}
