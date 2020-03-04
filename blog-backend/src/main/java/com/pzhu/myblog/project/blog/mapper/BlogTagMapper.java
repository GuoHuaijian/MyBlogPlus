package com.pzhu.myblog.project.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.blog.domain.BlogTag;
import com.pzhu.myblog.project.blog.vo.TagDetail;

import java.util.List;

/**
 * 标签操作mapper层
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28
 */
public interface BlogTagMapper extends BaseMapper<BlogTag> {

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
     * 查询标签的博客关联数
     * @param id 标签id
     * @return
     */
    int selectContactCount(int id);

    /**
     * 查询标签的博客关联标题
     * @param id 标签id
     * @return
     */
    List<String> selectContactTitle(int id);

    /**
     * 分页查询
     * @param page 分页对象
     * @return
     */
    Page<TagDetail> selectPageVo(Page<?> page);
}
