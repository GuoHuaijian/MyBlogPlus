package com.pzhu.myblog.project.blog.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 文章分页视图层对象
 * @Author Guo Huaijian
 * @Date 2020/2/14 20:21
 */
@Data
public class BlogArticleVO {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章状态，1表示发表，2表示草稿箱，3表示垃圾箱
     */
    private String status;

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
    private Date createTime;

}
