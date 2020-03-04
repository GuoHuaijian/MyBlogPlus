package com.pzhu.myblog.project.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pzhu.myblog.project.blog.domain.BlogTag;
import com.pzhu.myblog.project.blog.vo.TagDetail;

import java.util.List;

/**
 * 标签操作service层
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28
 */
public interface BlogTagService extends IService<BlogTag>{

    /**
     * 查询全部标签
     * @return
     */
    List<BlogTag> selectAll();

    /**
     * 根据用户查询id
     * @param name
     * @return
     */
    List<Integer> selectByName(List<String> name);

    /**
     * 添加标签
     * @param blogTag {@link BlogTag} 标签对象
     * @return
     */
    int addTag(BlogTag blogTag);

    /**
     * 修改标签
     * @param blogTag {@link BlogTag} 标签对象
     * @return
     */
    int updateTag(BlogTag blogTag);

    /**
     * 删除标签
     * @param id
     * @return
     */
    int deleteTag(int id);

    /**
     * 查询标签的博客关联数和关联博客的标题
     * @param id 标签id
     * @return
     */
    TagDetail selectContact(int id);

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    BlogTag selectById(int id);

    /**
     * 分页查询
     * @param page 分页对象
     * @return
     */
    Page<TagDetail> selectPageVo(Page<?> page);
}
