package com.pzhu.myblog.project.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.user.domain.BlogUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户相关操作处理 数据层
 *
 * @Author Guo Huaijian
 * @Date 2020/1/15
 */
public interface BlogUserService extends IService<BlogUser>{

    /**
     * 通过账号查询用户信息
     * @param account
     * @return
     */
    BlogUser selectByAccount(String account);

    /**
     * 分页查询用户列表
     * @param page 分页对象
     * @return
     */
    Page<BlogUser> selectPageVo(Page<?> page);

    /**
     * 添加用户
     * @param blogUser
     * @return
     */
    int addUser(BlogUser blogUser);

    /**
     * 更新用户
     * @param blogUser
     * @return
     */
    int updateUser(BlogUser blogUser);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(int id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    BlogUser selectById(int id);

    /**
     * 添加角色
     * @param userId 用户id
     * @return
     */
    int addRole(int userId);
}
