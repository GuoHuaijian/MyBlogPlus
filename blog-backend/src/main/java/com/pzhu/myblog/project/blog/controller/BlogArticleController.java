package com.pzhu.myblog.project.blog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.framework.aspectj.lang.annotation.Log;
import com.pzhu.myblog.framework.aspectj.lang.enums.BusinessType;
import com.pzhu.myblog.project.blog.domain.BlogArticle;
import com.pzhu.myblog.project.blog.dto.BlogArticleDTO;
import com.pzhu.myblog.project.blog.service.BlogArticleService;
import com.pzhu.myblog.project.blog.vo.ArticleDetail;
import com.pzhu.myblog.project.blog.vo.BlogArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章处理
 *
 * @Author Guo Huaijian
 * @Date 2019/12/28 18:22
 */
@RestController
@RequestMapping("article")
@Api(value = "文章api",tags = "文章api")
public class BlogArticleController {

    @Autowired
    private BlogArticleService blogArticleService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @ApiOperation(value = "查询全部数据", notes = "不需要输入参数", httpMethod = "GET")
    @GetMapping(value = "list")
    public BaseResult selectAll() {
        List<BlogArticle> blogArticles = blogArticleService.selectByAll();
        return BaseResult.success(blogArticles);
    }

    /**
     * 根据条件查询文章
     *
     * @param title      标题
     * @param categoryId 分类id
     * @param status     文章状态
     * @return
     */
    @ApiOperation(value = "根据条件查询文章", notes = "参数可选", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "title", value = "标题", required = false, dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "分类", required = false, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "文章状态", required = false, dataType = "int")})
    @GetMapping(value = "select")
    public BaseResult select(@RequestParam(value = "title", required = false) String title,
                             @RequestParam(value = "categoryId", required = false) Integer categoryId,
                             @RequestParam(value = "status", required = false) Integer status) {
        List<BlogArticle> blogArticles = blogArticleService.select(title, categoryId, status);
        return BaseResult.success(blogArticles);
    }

    /**
     * 根据分类id查询文章
     *
     * @param categoryId 分类id
     * @return
     */
    @ApiOperation(value = "根据分类id查询文章", notes = "参数必填", httpMethod = "GET")
    @ApiImplicitParam(name = "categoryId", value = "分类id", required = false, dataType = "int")
    @GetMapping(value = "selectByCategoryId/{categoryId}")
    public List<BlogArticle> selectByCategoryId(@PathVariable int categoryId) {
        List<BlogArticle> blogArticles = blogArticleService.selectByCategoryId(categoryId);
        return blogArticles;
    }

    /**
     * 根据id删除文章
     * @param id 文章id
     * @return
     */
    @ApiOperation(value = "根据分类id删除文章", notes = "参数必填", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "int")
    @DeleteMapping(value = "delete/{id}")
    public BaseResult deleteById(@PathVariable int id){
        int i = blogArticleService.deleteById(id);
        // 判断是否删除成功
        if (i < 1){
            return BaseResult.success("删除失败！", CodeStatus.FAIL);
        } else {
            // 删除成功返回删除的数
            return BaseResult.success("删除成功！", CodeStatus.OK);
        }
    }

    /**
     * 添加文章
     * @param blogArticle {@link BlogArticle}
     * @return
     */
    @ApiOperation(value = "添加文章", notes = "参数必填", httpMethod = "POST")
    @ApiImplicitParam(name = "BlogArticle", value = "文章", required = true, dataType = "BlogArticle")
    @GetMapping(value = "insert")
    public BaseResult insert(@RequestBody BlogArticle blogArticle){
        int i = blogArticleService.insert(blogArticle);
        return BaseResult.success(i);
    }

    /**
     * 修改文章
     * @param blogArticle
     * @return
     */
    @PostMapping(value = "update")
    public BaseResult update(@RequestBody BlogArticle blogArticle){
        int i = blogArticleService.update(blogArticle);
        // 返回值小于0则没有修改成功
        if (i <= 0){
           return BaseResult.error("修改失败");
        } else {
            return BaseResult.success("修改成功",i);
        }
    }

    /**
     * 分页查询文章列表
     * @param currentPage 当前页 默认为1
     * @param pageSize 每页大小 默认为10
     * @return
     */
    @Log(title = "查询文章分页",businessType = BusinessType.OTHER)
    @GetMapping(value = "page/{currentPage}/{pageSize}")
    public BaseResult selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<BlogArticleVO> page = new Page<>(currentPage,pageSize);
        Page<BlogArticleVO> pageVo = blogArticleService.selectPageVo(page);
        return BaseResult.success(CodeStatus.OK,pageVo,"请求成功");
    }

    /**
     * 添加文章
     * @param blogArticleDTO {@link BlogArticleDTO}
     * @return
     */
    @PostMapping("add")
    public BaseResult addArticle(@RequestBody BlogArticleDTO blogArticleDTO) {
        int i = blogArticleService.addArticle(blogArticleDTO);
        if (i > 0) {
            return BaseResult.success("请求成功", CodeStatus.OK);
        } else {
            return BaseResult.error("添加失败");
        }
    }

    /**
     * 查询文章全部信息
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    public BaseResult selectById(@PathVariable int id){
        ArticleDetail articleDetail = blogArticleService.selectById(id);
        return BaseResult.success(CodeStatus.OK,articleDetail,"请求成功");
    }

    /**
     * 获取修改文章的回显信息
     * @param id
     * @return
     */
    @GetMapping("detail2/{id}")
    public BaseResult selectByUpdateId(@PathVariable int id){
        BlogArticleDTO articleDTO = new BlogArticleDTO();
        ArticleDetail articleDetail = blogArticleService.selectById(id);
        articleDTO.setAuthor(articleDetail.getAuthor());
        articleDTO.setTitle(articleDetail.getTitle());
        articleDTO.setCategory(articleDetail.getCategory());
        articleDTO.setContent(articleDetail.getContent());
        articleDTO.setSummary(articleDetail.getSummary());
        articleDTO.setSummary_img(articleDetail.getSummaryImg());
        articleDTO.setTag(articleDetail.getTag());

        return BaseResult.success(CodeStatus.OK,articleDTO,"请求成功");
    }

    /**
     * 修改文章信息
     * @param blogArticleDTO
     * @param id
     * @return
     */
    @PutMapping("update/{id}")
    public BaseResult update(@RequestBody BlogArticleDTO blogArticleDTO,@PathVariable int id){
        int i = blogArticleService.update(blogArticleDTO, id);
        return BaseResult.success("修改成功",CodeStatus.OK);
    }

}

