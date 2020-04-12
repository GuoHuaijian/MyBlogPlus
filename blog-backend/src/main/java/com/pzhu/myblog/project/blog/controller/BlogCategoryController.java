package com.pzhu.myblog.project.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.project.blog.domain.BlogCategory;
import com.pzhu.myblog.project.blog.service.BlogCategoryService;
import com.pzhu.myblog.project.blog.vo.BlogCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 分类操作controller层
 * @Author Guo Huaijian
 * @Date 2020/1/12 20:27
 */
@RestController
@RequestMapping("category")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    /**
     * 查询全部分类
     * @return
     */
    @GetMapping("list")
    public BaseResult selectAll(){
        List<BlogCategory> blogCategories = blogCategoryService.selectAll();
        List<BlogCategoryVO> blogCategoryVOS = new ArrayList<>();
        for (BlogCategory category : blogCategories) {
            BlogCategoryVO blogCategoryVO = new BlogCategoryVO();
            blogCategoryVO.setId(category.getId());
            blogCategoryVO.setName(category.getCategoryTitle());
            blogCategoryVOS.add(blogCategoryVO);
        }
        return BaseResult.success(CodeStatus.OK,blogCategoryVOS,"请求成功");
    }

    /**
     * 分页查询类别列表
     * @param currentPage 当前页 默认为1
     * @param pageSize 每页大小 默认为10
     * @return
     */
    @GetMapping(value = "page/{currentPage}/{pageSize}")
    public BaseResult selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<BlogCategory> page = new Page<>(currentPage,pageSize);
        Page<BlogCategory> pageVo = blogCategoryService.selectPageVo(page);
        return BaseResult.success(CodeStatus.OK,pageVo,"请求成功");
    }

    /**
     * 查询分类信息
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    public BaseResult selectById(@PathVariable int id){
        BlogCategory blogCategory = blogCategoryService.selectById(id);
        return BaseResult.success(CodeStatus.OK,blogCategory,"请求成功");
    }

    /**
     * 添加分类
     * @param blogCategory
     * @return
     */
    @PostMapping("add")
    public BaseResult addCategory(@RequestBody BlogCategory blogCategory){
        int i = blogCategoryService.addCategory(blogCategory);
        return BaseResult.success("添加成功",CodeStatus.OK);
    }

    /**
     * 修改分类
     * @param blogCategory
     * @param id
     * @return
     */
    @PutMapping("update/{id}")
    public BaseResult updateCategory(@RequestBody BlogCategory blogCategory,@PathVariable int id){
        blogCategory.setId(id);
        blogCategory.setUpdateTime(new Date());
        int i = blogCategoryService.updateCategory(blogCategory);
        return BaseResult.success("请求成功",CodeStatus.OK);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public BaseResult deleteCategory(@PathVariable int id){
        int i = blogCategoryService.deleteCategory(id);
        return BaseResult.success("删除成功", CodeStatus.OK);
    }
}
