package com.pzhu.myblog.project.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.blog.domain.BlogCategory;

import java.util.List;

/**
 * 分类操作mapper层
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28
 */
public interface BlogCategoryMapper extends BaseMapper<BlogCategory> {

    /**
     * 查询全部分类
     * @return
     */
   List<BlogCategory> selectAll();

    /**
     * 根据分类名查询
     * @param name
     * @return
     */
   BlogCategory selectByName(String name);

    /**
     * <p>
     * 查询 : 根据state状态查询用户列表，分页显示
     * </p>
     *
     * @param page 分页对象,传递参数给 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @return 分页对象
     */
    Page<BlogCategory> selectPageVo(Page<?> page);


}
