package com.pzhu.myblog.project.monitor.service;

import com.pzhu.myblog.project.monitor.domain.LoginUser;
import com.pzhu.myblog.project.user.domain.BlogUser;

/**
 * 用户登录信息
 * @author Guo Huaijian
 * @Date 2020/2/24 16:04
 */
public interface LoginUserService {

    /**
     * 保存用户登录信息
     * @param blogUser
     * @param token
     * @return
     */
    LoginUser saveUser(BlogUser blogUser,String token);
}
