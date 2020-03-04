package com.pzhu.myblog.project.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzhu.myblog.project.system.domain.BlogCarousel;
import com.pzhu.myblog.project.system.mapper.BlogCarouselMapper;
import com.pzhu.myblog.project.system.service.BlogCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮播图实现层
 *
 * @Author Guo Huaijian
 * @Date 2020/2/24 22:09
 */
@Service
public class BlogCarouselServiceImpl extends ServiceImpl<BlogCarouselMapper, BlogCarousel> implements BlogCarouselService{

    @Autowired
    private BlogCarouselMapper blogCarouselMapper;

    /**
     * 查询轮播图信息
     * @param id 轮播图ID
     * @return
     */
    @Override
    public BlogCarousel selectBlogCarouselById(int id) {
        return blogCarouselMapper.selectBlogCarouselById(id);
    }

    /**
     * 查询轮播图列表
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<BlogCarousel> selectBlogCarouselList(Page<?> page) {
        return blogCarouselMapper.selectBlogCarouselList(page);
    }

    /**
     * 新增轮播图
     * @param blogCarousel 轮播图信息
     * @return
     */
    @Override
    public int insertBlogCarousel(BlogCarousel blogCarousel) {
        return blogCarouselMapper.insertBlogCarousel(blogCarousel);
    }

    /**
     * 修改公告
     * @param blogCarousel 轮播图信息
     * @return
     */
    @Override
    public int updateBlogCarousel(BlogCarousel blogCarousel) {
        return blogCarouselMapper.updateBlogCarousel(blogCarousel);
    }

    /**
     * 删除轮播图
     * @param id 轮播图ID
     * @return
     */
    @Override
    public int deleteBlogCarouselById(int id) {
        return blogCarouselMapper.deleteBlogCarouselById(id);
    }

    /**
     * 批量删除轮播图信息
     * @param Ids 需要删除的轮播图ID
     * @return
     */
    @Override
    public int deleteBlogCarouselByIds(List<Integer> Ids) {
        return blogCarouselMapper.deleteBlogCarouselByIds(Ids);
    }
}
