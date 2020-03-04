package com.pzhu.myblog.project.blog.vo;

import lombok.Data;

/**
 * 分类视图层对象
 *
 * @Author Guo Huaijian
 * @Date 2020/2/16 13:41
 */
@Data
public class BlogCategoryVO {

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名
     */
    private String name;
}
