package com.pzhu.myblog.project.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.common.utils.StringUtils;
import com.pzhu.myblog.project.user.domain.BlogUser;
import com.pzhu.myblog.project.user.dto.UserInfo;
import com.pzhu.myblog.project.user.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息处理
 * @Author Guo Huaijian
 * @Date 2020/2/13 12:11
 */
@RestController
@RequestMapping("user")
public class BlogUserController {

    @Autowired
    private BlogUserService blogUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 获取用户信息
     * @return
     */
    @CrossOrigin(value = "*",maxAge = 3600)
    @GetMapping("info")
    public BaseResult getUserInfo(){
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        BlogUser blogUser = blogUserService.selectByAccount(account);
        String name = blogUser.getUserName();
        String avatar = blogUser.getAvatar();
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setAvatar(avatar);
        return BaseResult.success(CodeStatus.OK,userInfo,"请求成功");
    }

    /**
     *分页查询用户
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    @GetMapping(value = "page/{currentPage}/{pageSize}")
    public BaseResult selectPageVo(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<BlogUser> page = new Page<>(currentPage,pageSize);
        Page<BlogUser> blogUserPage = blogUserService.selectPageVo(page);
        return BaseResult.success(CodeStatus.OK,blogUserPage,"请求成功");
    }

    /**
     * 添加用户
     * @param blogUser
     * @return
     */
    @PostMapping("add")
    public BaseResult addUser(@RequestBody BlogUser blogUser) {
        // 密码加密
        if (!StringUtils.isEmpty(blogUser.getPassword())){
            String encode = passwordEncoder.encode(blogUser.getPassword());
            blogUser.setPassword(encode);
        }
        int i = blogUserService.addUser(blogUser);
        return BaseResult.success("添加成功",CodeStatus.OK);
    }

    /**
     * 更新用户
     * @param blogUser
     * @return
     */
    @PutMapping("update")
    public BaseResult updateUser(@RequestBody BlogUser blogUser) {
        // 密码加密
        if (!StringUtils.isEmpty(blogUser.getPassword())){
            String encode = passwordEncoder.encode(blogUser.getPassword());
            blogUser.setPassword(encode);
        }
       blogUserService.updateUser(blogUser);
       return BaseResult.success("修改成功",CodeStatus.OK);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public BaseResult deleteUser(@PathVariable int id) {
        blogUserService.deleteUser(id);
        return BaseResult.success("删除成功", CodeStatus.OK);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    public BaseResult selectById(@PathVariable int id){
        BlogUser blogUser = blogUserService.selectById(id);
        return BaseResult.success(CodeStatus.OK,blogUser,"请求成功");
    }
}
