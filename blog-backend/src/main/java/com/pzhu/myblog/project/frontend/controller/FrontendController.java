package com.pzhu.myblog.project.frontend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.common.constant.HttpStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.framework.aspectj.lang.annotation.VLog;
import com.pzhu.myblog.project.blog.domain.BlogCategory;
import com.pzhu.myblog.project.blog.domain.BlogTag;
import com.pzhu.myblog.project.blog.service.BlogArticleService;
import com.pzhu.myblog.project.blog.service.BlogCategoryService;
import com.pzhu.myblog.project.blog.service.BlogTagService;
import com.pzhu.myblog.project.blog.vo.ArticleDetail;
import com.pzhu.myblog.project.frontend.domain.Archive;
import com.pzhu.myblog.project.frontend.domain.Article;
import com.pzhu.myblog.project.frontend.service.FrontendService;
import com.pzhu.myblog.project.system.domain.BlogCarousel;
import com.pzhu.myblog.project.user.domain.BlogUser;
import com.pzhu.myblog.project.user.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台数据
 * @Author Guo Huaijian
 * @Date 2020/2/28 14:45
 */
@RestController
@RequestMapping("frontend")
public class FrontendController {

    @Autowired
    private FrontendService frontendService;

    @Autowired
    private BlogTagService tagService;

    @Autowired
    private BlogUserService userService;

    @Autowired
    private BlogCategoryService categoryService;

    @Autowired
    private BlogArticleService articleService;

    /**
     * 获取轮播图
     * @return
     */
    @VLog(title = "首页")
    @GetMapping("carousel")
    public BaseResult carousel(){
        List<BlogCarousel> blogCarousels = frontendService.carouselList();
        return BaseResult.success(HttpStatus.SUCCESS,blogCarousels,"请求成功");
    }

    /**
     * 查询文章分页数据
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    @GetMapping(value = "page/{currentPage}/{pageSize}")
    public BaseResult articlePag(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<Article> page = new Page<>(currentPage,pageSize);
        Page<Article> articlePage = frontendService.articlePage(page);
        return BaseResult.success(HttpStatus.SUCCESS,articlePage,"请求成功");
    }

    /**
     * 获取标签列表
     * @return
     */
    @GetMapping(value = "tag")
    public BaseResult tagList(){
        List<BlogTag> blogTags = tagService.selectAll();
        return BaseResult.success(HttpStatus.SUCCESS,blogTags,"请求成功");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping(value = "user")
    public BaseResult getUserInfo(){
        BlogUser blogUser = userService.selectById(2);
        return BaseResult.success(HttpStatus.SUCCESS,blogUser,"请求成功");
    }

    /**
     * 获取分类
     * @return
     */
    @GetMapping(value = "category")
    public BaseResult getCategory(){
        List<BlogCategory> blogCategories = categoryService.selectAll();
        return BaseResult.success(HttpStatus.SUCCESS,blogCategories,"请求成功");
    }

    /**
     * 获取文章详情
     * @param id 文章id
     * @return
     */
    @GetMapping(value = "article/{id}")
    public BaseResult getArticleDetail(@PathVariable int id){
        ArticleDetail articleDetail = articleService.selectById(id);
        return BaseResult.success(HttpStatus.SUCCESS,articleDetail,"请求成功");
    }

    /**
     * 查询归档信息
     * @return
     */
    @VLog(title = "归档")
    @GetMapping(value = "archive")
    public BaseResult archiveList(){
        List<Archive> archives = frontendService.archiveList();
        return BaseResult.success(HttpStatus.SUCCESS,archives,"请求成功");
    }

    /**
     * 查询自我简介
     * @return
     */
    @VLog(title = "自我简介")
    @GetMapping(value = "about")
    public BaseResult aboutMe(){
        String aboutMe = frontendService.aboutMe();
        return BaseResult.success(HttpStatus.SUCCESS,aboutMe,"请求成功");
    }
}
