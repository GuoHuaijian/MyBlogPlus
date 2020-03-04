package com.pzhu.myblog.project.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzhu.myblog.project.blog.domain.BlogArticleTag;
import com.pzhu.myblog.project.blog.mapper.BlogArticleTagMapper;
import com.pzhu.myblog.project.blog.service.BlogArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章标签中间表service实现层
 *
 * @Author Guo Huaijian
 * @Date 2019/2/17 11:13
 */
@Service
public class BlogArticleTagServiceImpl extends ServiceImpl<BlogArticleTagMapper, BlogArticleTag> implements BlogArticleTagService{

    @Autowired
    private BlogArticleTagMapper blogArticleTagMapper;

    /**
     * 根据文章id添加文章标签中间表
     * @param aid 文章id
     * @param tid  标签id
     * @return
     */
    @Override
    public int insert(int aid, List<Integer> tid) {
        return blogArticleTagMapper.insert(aid,tid);
    }

    /**
     * 根据文章id删除关联表
     * @param id 文章id
     * @return
     */
    @Override
    public int delete(int id) {
        return blogArticleTagMapper.delete(id);
    }

    /**
     * 根据文章id查询对应的标签
     * @param id 文章id
     * @return
     */
    @Override
    public List<String> SelectById(Integer id) {
        return blogArticleTagMapper.SelectById(id);
    }

}
