package com.pzhu.myblog.project.user.dto;

import lombok.Data;

/**
 * 登录参数
 * @Author Guo Huaijian
 * @Date 2020/2/13 11:43
 */
@Data
public class LoginParam {

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
