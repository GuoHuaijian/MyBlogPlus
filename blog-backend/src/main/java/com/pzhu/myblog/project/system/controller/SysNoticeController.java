package com.pzhu.myblog.project.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.project.system.domain.SysNotice;
import com.pzhu.myblog.project.system.service.SysNoticeService;
import com.pzhu.myblog.project.user.domain.BlogUser;
import com.pzhu.myblog.project.user.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 通知控制层
 *
 * @Author Guo Huaijian
 * @Date 2020/2/24 22:12
 */
@RestController
@RequestMapping("notice")
public class SysNoticeController {

    @Autowired
    private SysNoticeService noticeService;

    @Autowired
    private BlogUserService blogUserService;

    /**
     * 获取通知公告列表
     * @return
     */
    @GetMapping("page/{currentPage}/{pageSize}")
    public BaseResult list(@PathVariable int currentPage, @PathVariable int pageSize) {
        Page<SysNotice> page = new Page<>(currentPage,pageSize);
        Page<SysNotice> sysNoticePage = noticeService.selectNoticeList(page);
        return BaseResult.success(CodeStatus.OK,sysNoticePage,"请求成功");
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @GetMapping(value = "detail/{noticeId}")
    public BaseResult getInfo(@PathVariable Long noticeId) {
        return BaseResult.success(CodeStatus.OK,noticeService.selectNoticeById(noticeId),"请求成功");
    }

    /**
     * 新增通知公告
     */
    @PostMapping
    public BaseResult add(@Validated @RequestBody SysNotice notice) {
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        BlogUser blogUser = blogUserService.selectByAccount(account);
        String userName = blogUser.getUserName();
        notice.setCreateBy(userName);
        noticeService.insertNotice(notice);
        return BaseResult.success("请求成功",CodeStatus.OK);
    }

    /**
     * 修改通知公告
     */
    @PutMapping
    public BaseResult edit(@Validated @RequestBody SysNotice notice) {
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        BlogUser blogUser = blogUserService.selectByAccount(account);
        String userName = blogUser.getUserName();
        notice.setUpdateBy(userName);
        noticeService.updateNotice(notice);
        return BaseResult.success("请求成功",CodeStatus.OK);
    }

    /**
     * 删除通知公告
     */
    @DeleteMapping("/{noticeId}")
    public BaseResult remove(@PathVariable Long noticeId) {
        noticeService.deleteNoticeById(noticeId);
        return BaseResult.success("请求成功",CodeStatus.OK);
    }
}
