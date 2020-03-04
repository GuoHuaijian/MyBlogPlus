package com.pzhu.myblog.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.system.domain.BlogCarousel;

import java.util.List;

/**
 * 轮播图mapper层
 *
 * @Author Guo Huaijian
 * @Date 2020/2/24 22:09
 */
public interface BlogCarouselMapper extends BaseMapper<BlogCarousel> {

    /**
     * 查询轮播图信息
     *
     * @param id 轮播图ID
     * @return 轮播图信息
     */
    BlogCarousel selectBlogCarouselById(int id);

    /**
     * 查询轮播图列表
     * @param page 分页对象
     * @return 轮播图集合
     */
    Page<BlogCarousel> selectBlogCarouselList(Page<?> page);

    /**
     * 新增轮播图
     *
     * @param blogCarousel 轮播图信息
     * @return 结果
     */
    int insertBlogCarousel(BlogCarousel blogCarousel);

    /**
     * 修改公告
     *
     * @param blogCarousel 轮播图信息
     * @return 结果
     */
    int updateBlogCarousel(BlogCarousel blogCarousel);

    /**
     * 批量删除轮播图
     *
     * @param id 轮播图ID
     * @return 结果
     */
    int deleteBlogCarouselById(int id);

    /**
     * 批量删除轮播图信息
     *
     * @param Ids 需要删除的轮播图ID
     * @return 结果
     */
    int deleteBlogCarouselByIds(List<Integer> Ids);
}
