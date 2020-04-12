package com.pzhu.myblog.project.frontend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.frontend.domain.Archive;
import com.pzhu.myblog.project.frontend.domain.Article;
import com.pzhu.myblog.project.frontend.mapper.FrontendMapper;
import com.pzhu.myblog.project.frontend.service.FrontendService;
import com.pzhu.myblog.project.system.domain.BlogCarousel;
import com.pzhu.myblog.project.system.mapper.BlogCarouselMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Guo Huaijian
 * @Date 2020/2/28 14:51
 */
@Service
public class FrontendServiceImpl implements FrontendService {

    @Autowired
    private FrontendMapper frontendMapper;

    @Autowired
    private BlogCarouselMapper carouselMapper;

    /**
     * 获取轮播图
     * @return
     */
    @Override
    public List<BlogCarousel> carouselList() {
        return carouselMapper.selectList(null);
    }

    /**
     * 分页查询文章列表
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<Article> articlePage(Page<?> page) {
        return frontendMapper.articlePage(page);
    }

    /**
     * 查询归档信息
     * @return
     */
    @Override
    public List<Archive> archiveList() {
        return frontendMapper.archiveList();
    }

    /**
     * 查询自我简介
     * @return
     */
    @Override
    public String aboutMe() {
        return frontendMapper.aboutMe();
    }
}
