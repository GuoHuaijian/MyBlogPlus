package com.pzhu.myblog.project.monitor.controller;

import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.framework.web.domain.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Guo Huaijian
 * @Date 2020/2/24 14:46
 */
@RestController
@RequestMapping("monitor")
public class ServerController {

    @GetMapping("server")
    public BaseResult getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return BaseResult.success(CodeStatus.OK,server,"请求成功");
    }
}
