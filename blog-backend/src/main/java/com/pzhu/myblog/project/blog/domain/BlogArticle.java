package com.pzhu.myblog.project.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章表
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28
 */
@Data
@TableName(value = "blog_article")
public class BlogArticle implements Serializable {
    /**
     * 文章id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类id
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 文章标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 文章简介
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 文章正文
     */
    @TableField(value = "content")
    private String content;

    /**
     * 文章作者
     */
    @TableField(value = "author")
    private String author;

    /**
     * 作者头像url
     */
    @TableField(value = "author_img")
    private String authorImg;

    /**
     * 简介显示图片的url
     */
    @TableField(value = "summary_img")
    private String summaryImg;

    /**
     * 文章状态，1表示发表，2表示草稿箱，3表示垃圾箱
     */
    @TableField(value = "status")
    private String status;

    /**
     * 是否设为推荐，1表示推荐，2表示不推荐
     */
    @TableField(value = "support")
    private String support;

    /**
     * 点击量
     */
    @TableField(value = "click_num")
    private Integer clickNum;

    /**
     * 创建时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_TITLE = "title";

    public static final String COL_SUMMARY = "summary";

    public static final String COL_CONTENT = "content";

    public static final String COL_AUTHOR = "author";

    public static final String COL_AUTHOR_IMG = "author_img";

    public static final String COL_SUMMARY_IMG = "summary_img";

    public static final String COL_STATUS = "status";

    public static final String COL_SUPPORT = "support";

    public static final String COL_CLICK_NUM = "click_num";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
