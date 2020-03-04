package com.pzhu.myblog.project.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pzhu.myblog.project.blog.domain.BlogArticleTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章标签中间表mapper层
 *
 * @Author Guo Huaijian
 * @Date 2019/2/17 11:13
 */
public interface BlogArticleTagMapper extends BaseMapper<BlogArticleTag> {

    /**
     * 根据文章id添加文章标签中间表
     * @param aid 文章id
     * @param tid  标签id
     * @return
     */
    int insert(@Param("aid") int aid, @Param("list") List<Integer> tid);

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
