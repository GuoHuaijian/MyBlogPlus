package com.pzhu.myblog.framework.oauth2.server;

import com.google.common.collect.Lists;
import com.pzhu.myblog.project.permission.domain.BlogPermission;
import com.pzhu.myblog.project.permission.service.BlogPermissionService;
import com.pzhu.myblog.project.user.domain.BlogUser;
import com.pzhu.myblog.project.user.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义用户认证
 * @Author Guo Huaijian
 * @Date 2020/2/12 17:12
 */
@Service(value = "UserDetails")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BlogUserService blogUserService;

    @Autowired
    private BlogPermissionService blogPermissionService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BlogUser blogUser = blogUserService.selectByAccount(s);
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        if (blogUser != null){
            List<BlogPermission> blogPermissions = blogPermissionService.selectByUserId(blogUser.getId().longValue());
            blogPermissions.forEach(blogPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(blogPermission.getName());
                grantedAuthorities.add(grantedAuthority);
            });
            // 框架实现认证工作
            return new User(blogUser.getAccount(),blogUser.getPassword(),grantedAuthorities);
        }
        return null;
    }
}
