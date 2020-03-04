package com.pzhu.myblog.framework.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.utils.StringUtils;
import com.pzhu.myblog.framework.web.page.PageDomain;
import com.pzhu.myblog.framework.web.page.SqlUtil;
import com.pzhu.myblog.framework.web.page.TableDataInfo;
import com.pzhu.myblog.framework.web.page.TableSupport;

import java.util.List;

/**
 * web层通用数据处理
 * @Author Guo Huaijian
 * @Date 2020/2/24 17:55
 */
public class BaseController
{

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(CodeStatus.OK);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

}
