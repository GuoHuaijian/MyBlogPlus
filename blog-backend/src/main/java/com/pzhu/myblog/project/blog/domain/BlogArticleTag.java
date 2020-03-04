package com.pzhu.myblog.project.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章标签中间表
 *
 * @Author Guo Huaijian
 * @Date 2019/2/17 11:13
 */
@Data
@TableName(value = "blog_article_tag")
public class BlogArticleTag implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    private Integer articleId;

    /**
     * 标签id
     */
    @TableField(value = "tag_id")
    private Integer tagId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ARTICLE_ID = "article_id";

    public static final String COL_TAG_ID = "tag_id";
}
