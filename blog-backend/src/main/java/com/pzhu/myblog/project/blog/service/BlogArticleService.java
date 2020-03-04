package com.pzhu.myblog.project.blog.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pzhu.myblog.project.blog.domain.BlogArticle;
import com.pzhu.myblog.project.blog.dto.BlogArticleDTO;
import com.pzhu.myblog.project.blog.vo.ArticleDetail;
import com.pzhu.myblog.project.blog.vo.BlogArticleVO;

import java.util.List;

/**
 * 文章相关操作处理 服务层
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28
 */
public interface BlogArticleService extends IService<BlogArticle>{

    /**
     *查询所有文章信息
     * @return
     */
    List<BlogArticle> selectByAll();

    /**
     * 根据条件查询文章
     * @param title 标题
     * @param categoryId 分类id
     * @param status 文章状态
     * @return
     */
    List<BlogArticle> select(String title, Integer categoryId, Integer status);

    /**
     * 根据分类id查询文章
     *
     * @param categoryId 分类id
     * @return
     */
    List<BlogArticle> selectByCategoryId(int categoryId);

    /**
     * 根据id删除文章
     * @param id 文章id
     * @return
     */
    int deleteById(int id);

    /**
     * 添加文章
     * @param blogArticle {@link BlogArticle}
     * @return
     */
    int insert( BlogArticle blogArticle);

    /**
     * 根据分类id修改文章
     *
     * @param blogArticle 文章信息
     * @return
     */
    int update(BlogArticle blogArticle);

    /**
     *根据分页条件查询文章
     * @param page 分页对象
     * @return
     */
    Page<BlogArticleVO> selectPageVo(Page<?> page);

    /**
     * 添加文章
     * @param blogArticleDTO {@link BlogArticleDTO}
     * @return
     */
    int addArticle(BlogArticleDTO blogArticleDTO);

    /**
     * 根据文章id查询文章
     * @param id 文章id
     * @return
     */
    ArticleDetail selectById(int id);

    /**
     * 修改文章信息
     * @param articleDTO {@link BlogArticleDTO}前台数据
     * @param id 文章id
     * @return
     */
    int update(BlogArticleDTO articleDTO,int id);
}
