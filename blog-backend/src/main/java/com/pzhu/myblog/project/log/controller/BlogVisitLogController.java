package com.pzhu.myblog.project.log.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.project.log.domain.BlogVisitLog;
import com.pzhu.myblog.project.log.service.BlogVisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 访问日志控制层
 * @Author Guo Huaijian
 * @Date 2020/2/24 14:03
 */
@RestController
@RequestMapping("visitLog")
public class BlogVisitLogController {

    @Autowired
    private BlogVisitLogService blogVisitLogService;

    /**
     *
     * 分页查询访问日志
     * @param currentPage 当前页
     * @param pageSize  每页大小
     * @return
     */
    @GetMapping(value = "page/{currentPage}/{pageSize}")
    public BaseResult selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<BlogVisitLog> page = new Page<>(currentPage,pageSize);
        Page<BlogVisitLog> blogVisitLogPage = blogVisitLogService.selectList(page);
        return BaseResult.success(CodeStatus.OK,blogVisitLogPage,"请求成功");
    }
}
