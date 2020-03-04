package com.pzhu.myblog.project.user.dto;

import lombok.Data;

/**
 * 用户信息
 * @Author Guo Huaijian
 * @Date 2020/2/15 15:46
 */
@Data
public class UserInfo {

    /**
     * 用户名字
     */
    private String name;

    /**
     * 用户头像
     */
    private String avatar;
}
