package com.pzhu.myblog.project.log.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.project.log.domain.SysLogininfor;
import com.pzhu.myblog.project.log.service.SysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志控制层
 * @Author Guo Huaijian
 * @Date 2020/2/24 11:56
 */
@RestController
@RequestMapping(value = "loginLog")
public class SysLogininforController {

    @Autowired
    private SysLogininforService sysLogininforService;

    /**
     *
     * 分页查询登录日志
     * @param currentPage 当前页
     * @param pageSize  每页大小
     * @return
     */
    @GetMapping(value = "page/{currentPage}/{pageSize}")
    public BaseResult selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<SysLogininfor> page = new Page<>(currentPage,pageSize);
        Page<SysLogininfor> sysLogininfors = sysLogininforService.selectList(page);
        return BaseResult.success(CodeStatus.OK,sysLogininfors,"请求成功");
    }
}
