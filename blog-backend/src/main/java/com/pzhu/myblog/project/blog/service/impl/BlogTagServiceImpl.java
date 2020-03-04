package com.pzhu.myblog.project.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzhu.myblog.project.blog.domain.BlogTag;
import com.pzhu.myblog.project.blog.mapper.BlogTagMapper;
import com.pzhu.myblog.project.blog.service.BlogTagService;
import com.pzhu.myblog.project.blog.vo.TagDetail;
import com.pzhu.myblog.project.user.domain.BlogUser;
import com.pzhu.myblog.project.user.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author Guo Huaijian
 * @Date 2019/12/28
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService{

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private BlogUserService blogUserService;

    /**
     * 查询全部标签
     * @return
     */
    @Override
    public List<BlogTag> selectAll() {
        return blogTagMapper.selectAll();
    }

    /**
     * 根据用户查询id
     * @param name
     * @return
     */
    @Override
    public List<Integer> selectByName(List<String> name) {
        return blogTagMapper.selectByName(name);
    }

    /**
     * 添加标签
     * @param blogTag {@link BlogTag} 标签对象
     * @return
     */
    @Override
    public int addTag(BlogTag blogTag) {
        blogTag.setCreateTime(new Date());
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        BlogUser blogUser = blogUserService.selectByAccount(account);
        String name = blogUser.getUserName();
        blogTag.setCreator(name);
        return blogTagMapper.insert(blogTag);
    }

    /**
     * 修改标签
     * @param blogTag {@link BlogTag} 标签对象
     * @return
     */
    @Override
    public int updateTag(BlogTag blogTag) {
        return blogTagMapper.updateById(blogTag);
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @Override
    public int deleteTag(int id) {
        return blogTagMapper.deleteById(id);
    }

    /**
     * 查询标签的博客关联数和关联博客的标题
     * @param id 标签id
     * @return
     */
    @Override
    public TagDetail selectContact(int id) {
        TagDetail tagDetail = new TagDetail();
        tagDetail.setCount(blogTagMapper.selectContactCount(id));
        tagDetail.setBlogTitle(blogTagMapper.selectContactTitle(id));
        return tagDetail;
    }

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    @Override
    public BlogTag selectById(int id) {
        return blogTagMapper.selectById(id);
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<TagDetail> selectPageVo(Page<?> page) {
        return blogTagMapper.selectPageVo(page);
    }
}
