package com.pzhu.myblog.project.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.project.log.domain.SysLogininfor;

import java.util.List;

/**
 * 登录日志mapper层
 *
 * @Author Guo Huaijian
 * @Date 2019/2/22 13:22
 */
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    int deleteLogininforByIds(Long[] infoIds);

    /**
     * 清空系统登录日志
     *
     * @return 结果
     */
    int cleanLogininfor();

    /**
     * 分页查询系统登录日志集合
     * @param page 分页对象
     * @return 登录记录集合
     */
   Page<SysLogininfor> selectList(Page<?> page);
}
