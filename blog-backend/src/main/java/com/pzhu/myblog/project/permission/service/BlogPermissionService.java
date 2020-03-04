package com.pzhu.myblog.project.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzhu.myblog.project.permission.domain.BlogPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限
 *
 * @Author Guo Huaijian
 * @Date 2020/2/12 17:29
 */
public interface BlogPermissionService extends IService<BlogPermission>{

    /**
     * 根据用户id查询权限列表
     * @param id
     * @return
     */
    List<BlogPermission> selectByUserId(@Param("id") Long id);

}
