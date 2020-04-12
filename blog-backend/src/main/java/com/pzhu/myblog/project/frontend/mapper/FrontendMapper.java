package com.pzhu.myblog.project.frontend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.frontend.domain.Archive;
import com.pzhu.myblog.project.frontend.domain.Article;

import java.util.List;

/**
 * 前端mapper层
 * @Author Guo Huaijian
 * @Date 2020/3/2 12:40
 */
public interface FrontendMapper {

    /**
     * 分页查询文章列表
     * @param page 分页对象
     * @return
     */
    Page<Article> articlePage(Page<?> page);

    /**
     * 查询归档信息
     * @return
     */
     List<Archive> archiveList();

    /**
     * 查询自我简介
     * @return
     */
    String aboutMe();
}
