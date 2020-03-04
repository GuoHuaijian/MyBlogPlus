package com.pzhu.myblog.project.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzhu.myblog.project.permission.domain.BlogPermission;
import com.pzhu.myblog.project.permission.mapper.BlogPermissionMapper;
import com.pzhu.myblog.project.permission.service.BlogPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限
 *
 * @Author Guo Huaijian
 * @Date 2020/2/12 17:29
 */
@Service
public class BlogPermissionServiceImpl extends ServiceImpl<BlogPermissionMapper, BlogPermission> implements BlogPermissionService{

    @Autowired
    private BlogPermissionMapper blogPermissionMapper;
    /**
     * 根据用户信息查询用户权限列表
     * @param id
     * @return
     */
    @Override
    public List<BlogPermission> selectByUserId(Long id) {
        return blogPermissionMapper.selectByUserId(id);
    }
}
