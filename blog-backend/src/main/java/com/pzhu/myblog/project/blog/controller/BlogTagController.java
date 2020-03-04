package com.pzhu.myblog.project.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.project.blog.domain.BlogTag;
import com.pzhu.myblog.project.blog.service.BlogTagService;
import com.pzhu.myblog.project.blog.vo.BlogTagVO;
import com.pzhu.myblog.project.blog.vo.TagDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 标签操作controller层
 *
 * @Author Guo Huaijian
 * @Date 2020/1/12 20:44
 */
@RestController
@RequestMapping("tag")
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    /**
     * 查询操作
     * @return
     */
    @GetMapping("list")
    public BaseResult selectAll(){
        List<BlogTag> blogTags = blogTagService.selectAll();
        List<BlogTagVO> blogTagVOS = new ArrayList<>();
        for (BlogTag tag : blogTags) {
            BlogTagVO blogTagVO = new BlogTagVO();
            blogTagVO.setId(tag.getId());
            blogTagVO.setName(tag.getTagTitle());
            blogTagVOS.add(blogTagVO);
        }
        return BaseResult.success(CodeStatus.OK,blogTagVOS,"请求成功");
    }

    /**
     * 添加标签
     * @param blogTag
     * @return
     */
    @PostMapping("add")
    public BaseResult addTag(@RequestBody BlogTag blogTag){
        int i = blogTagService.addTag(blogTag);
        return BaseResult.success("添加成功",CodeStatus.OK);
    }

    /**
     * 修改标签
     * @param blogTag
     * @param id
     * @return
     */
    @PutMapping("update/{id}")
    public BaseResult updateTag(@RequestBody BlogTag blogTag,@PathVariable int id){
        blogTag.setId(id);
        blogTag.setUpdateTime(new Date());
        int i = blogTagService.updateTag(blogTag);
        return BaseResult.success("修改成功",CodeStatus.OK);
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public BaseResult deleteTag(@PathVariable int id){
        int i = blogTagService.deleteTag(id);
        return BaseResult.success("删除成功",CodeStatus.OK);
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("page/{currentPage}/{pageSize}")
    public BaseResult selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<TagDetail> page = new Page<>(currentPage,pageSize);
        Page<TagDetail> blogTagPage = blogTagService.selectPageVo(page);
        return BaseResult.success(CodeStatus.OK,blogTagPage,"请求成功");
    }

    /**
     *查询标签
     * @return
     */
    @GetMapping("detail/{id}")
    public BaseResult selectContact(@PathVariable int id){
        BlogTag blogTag = blogTagService.selectById(id);
        TagDetail tagDetail = blogTagService.selectContact(id);
        tagDetail.setTagTitle(blogTag.getTagTitle());
        return BaseResult.success(CodeStatus.OK,tagDetail,"请求成功");
    }
}
