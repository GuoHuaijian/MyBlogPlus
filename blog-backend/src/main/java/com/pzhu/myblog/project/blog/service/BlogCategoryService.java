package com.pzhu.myblog.project.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pzhu.myblog.project.blog.domain.BlogCategory;

import java.util.List;

/**
 * 分类操作service层
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28
 */
public interface BlogCategoryService extends IService<BlogCategory>{

    /**
     * 查询全部分类
     * @return
     */
    List<BlogCategory> selectAll();

    /**
     * 根据分类名查询
     * @param name
     * @return
     */
    BlogCategory selectByName(String name);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlogCategory selectById(int id);

    /**
     * 分页查询
     * @param page 分页对象
     * @return
     */
    Page<BlogCategory> selectPageVo(Page<?> page);

    /**
     * 添加分类
     * @param blogCategory {@link BlogCategory} 分类对象
     * @return
     */
    int addCategory(BlogCategory blogCategory);

    /**
     * 修改分类
     * @param blogCategory {@link BlogCategory} 分类对象
     * @return
     */
    int updateCategory(BlogCategory blogCategory);

    /**
     * 删除分类
     * @param id
     * @return
     */
    int deleteCategory(int id);
}
