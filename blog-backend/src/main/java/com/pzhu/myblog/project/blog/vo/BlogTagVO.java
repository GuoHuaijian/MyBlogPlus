package com.pzhu.myblog.project.blog.vo;

import lombok.Data;

/**
 * 标签视图层对象
 * @Author Guo Huaijian
 * @Date 2020/2/16 14:03
 */
@Data
public class BlogTagVO {

    /**
     * 标签id
     */
    private Integer id;

    /**
     * 标签名
     */
    private String name;
}
