package com.pzhu.myblog.project.monitor.service.impl;

import com.pzhu.myblog.common.utils.ServletUtils;
import com.pzhu.myblog.common.utils.ip.AddressUtils;
import com.pzhu.myblog.common.utils.ip.IpUtils;
import com.pzhu.myblog.project.monitor.domain.LoginUser;
import com.pzhu.myblog.project.monitor.service.LoginUserService;
import com.pzhu.myblog.project.user.domain.BlogUser;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户登录信息
 * @Author Guo Huaijian
 * @Date 2020/2/24 16:07
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

    /**
     * 保存用户登录信息
     * @param blogUser
     * @param token
     * @return
     */
    @Override
    public LoginUser saveUser(BlogUser blogUser, String token) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(blogUser);
        loginUser.setToken(token);
        loginUser.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(IpUtils.getIpAddr(ServletUtils.getRequest())));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setLoginTime(new Date());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
        return loginUser;
    }
}
