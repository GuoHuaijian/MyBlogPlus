package com.pzhu.myblog.project.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzhu.myblog.common.utils.RedisUtils;
import com.pzhu.myblog.project.user.domain.BlogUser;
import com.pzhu.myblog.project.user.mapper.BlogUserMapper;
import com.pzhu.myblog.project.user.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户相关操作处理
 *
 * @Author Guo Huaijian
 * @Date 2020/1/15
 */
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements BlogUserService {

    @Autowired
    private BlogUserMapper blogUserMapper;

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 通过账号查询用户信息
     *
     * @param account
     * @return
     */
    @Override
    public BlogUser selectByAccount(String account) {
        BlogUser blogUser = null;
        blogUser = redisUtils.getCacheObject("userInfo");
        if (blogUser == null){
            blogUser = blogUserMapper.selectByAccount(account);
            redisUtils.setCacheObject("userInfo",blogUser,60, TimeUnit.SECONDS);
            return blogUser;
        } else {
            return blogUser;
        }

    }

    /**
     * 分页查询用户列表
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<BlogUser> selectPageVo(Page<?> page) {
        return blogUserMapper.selectPageVo(page);
    }

    /**
     * 添加用户
     * @param blogUser
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addUser(BlogUser blogUser) {
        blogUser.setUserType("1");
        blogUser.setCreateTime(new Date());
        blogUserMapper.insert(blogUser);
        int i = addRole(blogUser.getId());
        return i;
    }

    /**
     * 更新用户
     * @param blogUser
     * @return
     */
    @Override
    public int updateUser(BlogUser blogUser) {
        return blogUserMapper.updateUser(blogUser);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public int deleteUser(int id) {
        return blogUserMapper.deleteUser(id);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public BlogUser selectById(int id){
        return blogUserMapper.selectById(id);
    }

    /**
     * 添加角色
     * @param userId 用户id
     * @return
     */
    @Override
    public int addRole(int userId) {
        return blogUserMapper.addRole(userId);
    }
}
