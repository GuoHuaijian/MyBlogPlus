package com.pzhu.myblog.project.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.project.system.domain.BlogCarousel;
import com.pzhu.myblog.project.system.service.BlogCarouselService;
import com.pzhu.myblog.project.user.domain.BlogUser;
import com.pzhu.myblog.project.user.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 轮播图控制层
 * @Author Guo Huaijian
 * @Date 2020/2/24 22:12
 */
@RestController
@RequestMapping("carousel")
public class BlogCarouselController {

    @Autowired
    private BlogCarouselService carouselService;

    @Autowired
    private BlogUserService blogUserService;

    /**
     * 获取轮播图列表
     * @return
     */
    @GetMapping("page/{currentPage}/{pageSize}")
    public BaseResult list(@PathVariable int currentPage, @PathVariable int pageSize) {
        Page<BlogCarousel> page = new Page<>(currentPage,pageSize);
        Page<BlogCarousel> blogCarouselPage = carouselService.selectBlogCarouselList(page);
        return BaseResult.success(CodeStatus.OK,blogCarouselPage,"请求成功");
    }

    /**
     * 根据轮播图编号获取详细信息
     */
    @GetMapping(value = "detail/{id}")
    public BaseResult getInfo(@PathVariable int id) {
        return BaseResult.success(CodeStatus.OK,carouselService.selectBlogCarouselById(id),"请求成功");
    }

    /**
     * 新增轮播图
     */
    @PostMapping
    public BaseResult add(@Validated @RequestBody BlogCarousel blogCarousel) {
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        BlogUser blogUser = blogUserService.selectByAccount(account);
        String userName = blogUser.getUserName();
        blogCarousel.setCreator(userName);
        carouselService.insertBlogCarousel(blogCarousel);
        return BaseResult.success("请求成功", CodeStatus.OK);
    }

    /**
     * 修改轮播图
     */
    @PutMapping
    public BaseResult edit(@Validated @RequestBody BlogCarousel blogCarousel) {
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        BlogUser blogUser = blogUserService.selectByAccount(account);
        String userName = blogUser.getUserName();
        blogCarousel.setCreator(userName);
        carouselService.updateBlogCarousel(blogCarousel);
        return BaseResult.success("请求成功",CodeStatus.OK);
    }

    /**
     * 删除轮播图
     */
    @DeleteMapping("/{id}")
    public BaseResult remove(@PathVariable int id) {
       carouselService.deleteBlogCarouselById(id);
        return BaseResult.success("请求成功",CodeStatus.OK);
    }
}
