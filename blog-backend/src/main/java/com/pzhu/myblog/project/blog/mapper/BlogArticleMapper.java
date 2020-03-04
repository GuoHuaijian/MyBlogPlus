package com.pzhu.myblog.project.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.blog.domain.BlogArticle;
import com.pzhu.myblog.project.blog.vo.BlogArticleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章相关操作处理 数据层
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    /**
     * 查询所有文章信息
     *
     * @return
     */
    List<BlogArticle> selectByAll();

    /**
     * 根据条件查询文章
     *
     * @param title      标题
     * @param categoryId 分类id
     * @param status     文章状态
     * @return
     */
    List<BlogArticle> select(@Param("title") String title, @Param("categoryId") Integer categoryId, @Param("status") Integer status);

    /**
     * 根据分类id查询文章
     *
     * @param categoryId 分类id
     * @return
     */
    List<BlogArticle> selectByCategoryId(int categoryId);

    /**
     * <p>
     * 查询 : 根据state状态查询用户列表，分页显示
     * </p>
     *
     * @param page 分页对象,传递参数给 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @return 分页对象
     */
    Page<BlogArticleVO> selectPageVo(Page<?> page);

}
