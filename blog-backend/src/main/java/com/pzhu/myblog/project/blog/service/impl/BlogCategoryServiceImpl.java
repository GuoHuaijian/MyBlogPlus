package com.pzhu.myblog.project.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzhu.myblog.project.blog.domain.BlogCategory;
import com.pzhu.myblog.project.blog.mapper.BlogCategoryMapper;
import com.pzhu.myblog.project.blog.service.BlogCategoryService;
import com.pzhu.myblog.project.user.domain.BlogUser;
import com.pzhu.myblog.project.user.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 分类操作实现层
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService{

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Autowired
    private BlogUserService blogUserService;

    /**
     * 查询全部分类
     * @return
     */
    @Override
    public List<BlogCategory> selectAll() {
        return blogCategoryMapper.selectAll();
    }

    /**
     * 根据分类名查询
     * @param name
     * @return
     */
    @Override
    public BlogCategory selectByName(String name) {
        return blogCategoryMapper.selectByName(name);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public BlogCategory selectById(int id) {
        return blogCategoryMapper.selectById(id);
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<BlogCategory> selectPageVo(Page<?> page) {
        return blogCategoryMapper.selectPageVo(page);
    }

    /**
     * 添加分类
     * @param blogCategory {@link BlogCategory} 分类对象
     * @return
     */
    @Override
    public int addCategory(BlogCategory blogCategory) {
        blogCategory.setCreateTime(new Date());
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        BlogUser blogUser = blogUserService.selectByAccount(account);
        String name = blogUser.getUserName();
        blogCategory.setCreator(name);
        return blogCategoryMapper.insert(blogCategory);
    }

    /**
     * 修改分类
     * @param blogCategory {@link BlogCategory} 分类对象
     * @return
     */
    @Override
    public int updateCategory(BlogCategory blogCategory) {
        int i = blogCategoryMapper.updateById(blogCategory);
        return i;
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @Override
    public int deleteCategory(int id) {
        return blogCategoryMapper.deleteById(id);
    }
}
