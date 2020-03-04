package com.pzhu.myblog.project.log.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.project.log.domain.SysOperLog;
import com.pzhu.myblog.project.log.service.SysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志控制层
 * @Author Guo Huaijian
 * @Date 2020/2/24 12:43
 */
@RestController
@RequestMapping("operLog")
public class SysOperLogController {

    @Autowired
    private SysOperLogService sysOperLogService;

    /**
     *
     * 分页查询操作日志
     * @param currentPage 当前页
     * @param pageSize  每页大小
     * @return
     */
    @GetMapping(value = "page/{currentPage}/{pageSize}")
    public BaseResult selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<SysOperLog> page = new Page<>(currentPage,pageSize);
        Page<SysOperLog> sysOperLogPage = sysOperLogService.selectList(page);
        return BaseResult.success(CodeStatus.OK,sysOperLogPage,"请求成功");
    }

    /**
     * 根据id查询详细信息
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    public BaseResult selectById(@PathVariable int id){
        SysOperLog sysOperLog = sysOperLogService.selectOperLogById((long) id);
        return BaseResult.success(CodeStatus.OK,sysOperLog,"请求成功");
    }
}
