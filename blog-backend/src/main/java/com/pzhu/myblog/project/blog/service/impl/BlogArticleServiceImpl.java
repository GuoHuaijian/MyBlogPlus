package com.pzhu.myblog.project.blog.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzhu.myblog.common.constant.Constants;
import com.pzhu.myblog.common.utils.RedisUtils;
import com.pzhu.myblog.project.blog.domain.BlogArticle;
import com.pzhu.myblog.project.blog.domain.BlogCategory;
import com.pzhu.myblog.project.blog.dto.BlogArticleDTO;
import com.pzhu.myblog.project.blog.mapper.BlogArticleMapper;
import com.pzhu.myblog.project.blog.service.BlogArticleService;
import com.pzhu.myblog.project.blog.service.BlogArticleTagService;
import com.pzhu.myblog.project.blog.service.BlogCategoryService;
import com.pzhu.myblog.project.blog.service.BlogTagService;
import com.pzhu.myblog.project.blog.vo.ArticleDetail;
import com.pzhu.myblog.project.blog.vo.BlogArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 文章相关操作处理 服务处理层
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Autowired
    private BlogArticleMapper blogArticleMapper;

    @Autowired
    private BlogCategoryService blogCategoryService;

    @Autowired
    private BlogArticleTagService blogArticleTagService;

    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     *查询所有文章信息
     * @return
     */
    @Override
    public List<BlogArticle> selectByAll() {
        List<BlogArticle> blogArticles = blogArticleMapper.selectByAll();
        return blogArticles;
    }

    /**
     * 根据条件查询文章
     * @param title 标题
     * @param categoryId 分类id
     * @param status 文章状态
     * @return
     */
    @Override
    public List<BlogArticle> select(String title, Integer categoryId, Integer status) {
        List<BlogArticle> blogArticles = blogArticleMapper.select(title, categoryId, status);
        return blogArticles;
    }

    /**
     * 根据分类id查询文章
     *
     * @param categoryId 分类id
     * @return
     */
    @Override
    public List<BlogArticle> selectByCategoryId(int categoryId) {
        List<BlogArticle> blogArticles = blogArticleMapper.selectByCategoryId(categoryId);
        return blogArticles;
    }

    /**
     * 根据id删除文章
     * @param id 文章id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(int id) {
        blogArticleTagService.delete(id);
        int i = blogArticleMapper.deleteById(id);
        return i;
    }

    /**
     * 添加文章
     * @param blogArticle {@link BlogArticle}
     * @return
     */
    @Override
    public int insert(BlogArticle blogArticle) {
        int i = blogArticleMapper.insert(blogArticle);
        return i;
    }

    /**
     * 修改文章
     * @param blogArticle {@link BlogArticle}文章信息
     * @return
     */
    @Override
    public int update(BlogArticle blogArticle) {
        int i = blogArticleMapper.updateById(blogArticle);
        return i;
    }

    /**
     * 根据分页条件查询文章列表
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<BlogArticleVO> selectPageVo(Page<?> page) {
        Page<BlogArticleVO> pageVO = null;
         pageVO = redisUtils.getCacheObject("pageVO"+page.getCurrent());
        if (pageVO == null){
            pageVO = blogArticleMapper.selectPageVo(page);
            redisUtils.setCacheObject("pageVO"+page.getCurrent(),pageVO,60, TimeUnit.SECONDS);
            return pageVO;
        }else {
            return pageVO;
        }
    }

    /**
     * 添加文章
     * @param blogArticleDTO {@link BlogArticleDTO}
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addArticle(BlogArticleDTO blogArticleDTO) {
        BlogArticle blogArticle = new BlogArticle();

        // 根据前台传来的分类名获取分类id赋值给blogArticle实体添加数据库
        String category = blogArticleDTO.getCategory();
        BlogCategory blogCategory = blogCategoryService.selectByName(category);
        blogArticle.setCategoryId(blogCategory.getId());

        // 判断前台传来的文章中状态设置添加进数据库的文章状态
        if (Constants.STATUS.equals(blogArticleDTO.getStatus())){
            blogArticle.setStatus("1");
        }else {
            blogArticle.setStatus("2");
        }

        // 将前台传来的数据赋值给blogArticle实体
        blogArticle.setAuthor(blogArticleDTO.getAuthor());
        blogArticle.setContent(blogArticleDTO.getContent());
        blogArticle.setCreateTime(new Date());
        blogArticle.setSummary(blogArticleDTO.getSummary());
        blogArticle.setSummaryImg(blogArticleDTO.getSummary_img());
        blogArticle.setTitle(blogArticleDTO.getTitle());
        blogArticleMapper.insert(blogArticle);

        // 获取添加文章后的id
       int id =  blogArticle.getId();

        // 获取前台的标签列表 查询标签id添加文章和标签的中间表
        List<String> tag = blogArticleDTO.getTag();
        List<Integer> TagIds = blogTagService.selectByName(tag);
        int i = blogArticleTagService.insert(id, TagIds);

        return i;
    }

    /**
     * 根据文章id查询
     * @param id 文章id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ArticleDetail selectById(int id) {
        ArticleDetail articleDetail = new ArticleDetail();
        BlogArticle blogArticle = blogArticleMapper.selectById(id);

        // 根据文章分类id查询分类名并赋值给articleDetail
        BlogCategory blogCategory = blogCategoryService.selectById(blogArticle.getCategoryId());
        articleDetail.setCategory(blogCategory.getCategoryTitle());

        // 根据文章id查询对应的标签并赋值给articleDetail
        List<String> list = blogArticleTagService.SelectById(blogArticle.getId());
        articleDetail.setTag(list);

        articleDetail.setId(blogArticle.getId());
        articleDetail.setAuthor(blogArticle.getAuthor());
        articleDetail.setAuthorImg(blogArticle.getAuthorImg());
        articleDetail.setClickNum(blogArticle.getClickNum());
        articleDetail.setContent(blogArticle.getContent());
        articleDetail.setCreateTime(blogArticle.getCreateTime());
        articleDetail.setStatus(blogArticle.getStatus());
        articleDetail.setSummary(blogArticle.getSummary());
        articleDetail.setSummaryImg(blogArticle.getSummaryImg());
        articleDetail.setSupport(blogArticle.getSupport());
        articleDetail.setTitle(blogArticle.getTitle());
        articleDetail.setUpdateTime(blogArticle.getUpdateTime());

        return articleDetail;
    }

    /**
     * 修改文章信息
     * @param articleDTO {@link BlogArticleDTO}前台数据
     * @param id 文章id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(BlogArticleDTO articleDTO,int id) {

        BlogArticle blogArticle = new BlogArticle();

        // 根据前台传来的分类名获取分类id赋值给blogArticle实体添加数据库
        String category = articleDTO.getCategory();
        BlogCategory blogCategory = blogCategoryService.selectByName(category);
        blogArticle.setCategoryId(blogCategory.getId());

        // 判断前台传来的文章中状态设置添加进数据库的文章状态
        if (Constants.STATUS.equals(articleDTO.getStatus())){
            blogArticle.setStatus("1");
        }else {
            blogArticle.setStatus("2");
        }

        // 获取前台的标签列表 先删除中间表再插入新的数据
        List<String> tag = articleDTO.getTag();
        List<Integer> TagIds = blogTagService.selectByName(tag);
        blogArticleTagService.delete(id);
        blogArticleTagService.insert(id,TagIds);

        // 将前台传来的数据赋值给blogArticle实体
        blogArticle.setId(id);
        blogArticle.setAuthor(articleDTO.getAuthor());
        blogArticle.setContent(articleDTO.getContent());
        blogArticle.setUpdateTime(new Date());
        blogArticle.setSummary(articleDTO.getSummary());
        blogArticle.setSummaryImg(articleDTO.getSummary_img());
        blogArticle.setTitle(articleDTO.getTitle());
        int i = blogArticleMapper.updateById(blogArticle);

        return i;
    }


}
