package com.pzhu.myblog.project.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzhu.myblog.project.blog.domain.BlogArticleTag;

import java.util.List;

/**
 * 文章标签中间表service层
 *
 * @Author Guo Huaijian
 * @Date 2019/2/17 11:13
 */
public interface BlogArticleTagService extends IService<BlogArticleTag>{

    /**
     * 根据文章id添加文章标签中间表
     * @param aid 文章id
     * @param tid  标签id
     * @return
     */
    int insert(int aid, List<Integer> tid);

    /**
     * 根据文章id删除关联表
     * @param id 文章id
     * @return
     */
    int delete(int id);

    /**
     * 根据文章id查询对应的标签
     * @param id 文章id
     * @return
     */
    List<String> SelectById(Integer id);

}
