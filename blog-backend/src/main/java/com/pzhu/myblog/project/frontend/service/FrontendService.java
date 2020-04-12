package com.pzhu.myblog.project.frontend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.frontend.domain.Archive;
import com.pzhu.myblog.project.frontend.domain.Article;
import com.pzhu.myblog.project.system.domain.BlogCarousel;

import java.util.List;

/**
 * @Author Guo Huaijian
 * @Date 2020/2/28 14:51
 */
public interface FrontendService {

    /**
     * 获取轮播图
     * @return
     */
    List<BlogCarousel> carouselList();

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
