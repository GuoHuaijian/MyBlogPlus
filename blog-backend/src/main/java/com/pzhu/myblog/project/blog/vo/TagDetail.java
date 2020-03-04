package com.pzhu.myblog.project.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 标签详情
 *
 * @Author Guo Huaijian
 * @Date 2020/2/21 14:04
 */
@Data
public class TagDetail {

    /**
     * 标签id
     */
    private int id;

    /**
     * 标签
     */
    private String tagTitle;

    /**
     * 创建者
     */
    private String creator;

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

    /**
     * 博客关联数
     */
    private int count;

    /**
     * 关联的博客标题
     */
    private List<String> blogTitle;
}
