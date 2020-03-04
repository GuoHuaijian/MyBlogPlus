package com.pzhu.myblog.project.monitor.controller;

import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.constant.Constants;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.common.utils.RedisUtils;
import com.pzhu.myblog.common.utils.StringUtils;
import com.pzhu.myblog.framework.web.controller.BaseController;
import com.pzhu.myblog.framework.web.page.TableDataInfo;
import com.pzhu.myblog.project.monitor.domain.LoginUser;
import com.pzhu.myblog.project.monitor.domain.SysUserOnline;
import com.pzhu.myblog.project.monitor.service.SysUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 在线用户监控
 * @Author Guo Huaijian
 * @Date 2020/2/24 15:05
 */
@RestController
@RequestMapping("/monitor/online")
public class SysUserOnlineController extends BaseController {

    @Autowired
    private SysUserOnlineService userOnlineService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/list")
    public TableDataInfo list(String ipaddr, String userName)
    {
        Collection<String> keys = redisUtils.keys(Constants.LOGIN_TOKEN_KEY + "*");
        List<SysUserOnline> userOnlineList = new ArrayList<SysUserOnline>();
        for (String key : keys)
        {
            LoginUser user = redisUtils.getCacheObject(key);
            if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByInfo(ipaddr, userName, user));
                }
            }
            else if (StringUtils.isNotEmpty(ipaddr))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByIpaddr(ipaddr, user));
                }
            }
            else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotNull(user.getUser()))
            {
                if (StringUtils.equals(userName, user.getUsername()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByUserName(userName, user));
                }
            }
            else
            {
                userOnlineList.add(userOnlineService.loginUserToUserOnline(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));
        return getDataTable(userOnlineList);
    }

    /**
     * 强退用户
     */
    @DeleteMapping("/{tokenId}")
    public BaseResult forceLogout(@PathVariable String tokenId)
    {
        redisUtils.deleteObject(Constants.LOGIN_TOKEN_KEY + tokenId);
        return BaseResult.success(CodeStatus.OK);
    }
}
