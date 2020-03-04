package com.pzhu.myblog.project.user.controller;

import com.google.common.collect.Maps;
import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.constant.Constants;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.common.exception.BusinessException;
import com.pzhu.myblog.common.utils.MapperUtils;
import com.pzhu.myblog.common.utils.RedisUtils;
import com.pzhu.myblog.common.utils.http.OkHttpClientUtil;
import com.pzhu.myblog.framework.aspectj.lang.enums.BusinessStatus1;
import com.pzhu.myblog.framework.manager.AsyncManager;
import com.pzhu.myblog.framework.manager.factory.AsyncFactory;
import com.pzhu.myblog.project.monitor.domain.LoginUser;
import com.pzhu.myblog.project.monitor.service.LoginUserService;
import com.pzhu.myblog.project.user.domain.BlogUser;
import com.pzhu.myblog.project.user.dto.LoginParam;
import com.pzhu.myblog.project.user.service.BlogUserService;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户登录处理
 *
 * @Author Guo Huaijian
 * @Date 2020/1/15 14:17
 */
//@CrossOrigin(origins = "*",maxAge = 360)
@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private BlogUserService blogUserService;

    /**
     * 获取token地址
     */
    private static final String URL_OAUTH_TOKEN = "http://localhost:8080/oauth/token";

    @Value("${oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource(name = "UserDetails")
    public UserDetailsService userDetailsService;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private LoginUserService loginUserService;

    /**
     * 用户登录
     * @param loginParam {@link LoginParam}
     * @return
     */
    @PostMapping("/login")
    public BaseResult login(@RequestBody LoginParam loginParam) {
        // 封装返回的结果集
        Map<String, Object> result = Maps.newHashMap();

        // 验证密码是否正确
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (userDetails == null || !passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userDetails.getUsername(), Constants.LOGIN_FAIL,
                    "密码不正确"));
            throw new BusinessException(BusinessStatus1.ADMIN_PASSWORD);
        }

        // 通过 HTTP 客户端请求登录接口
        Map<String, String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        try {
            // 解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);


            // 获取当前用户 将当前用户信息保存在redis中
            BlogUser blogUser = blogUserService.selectByAccount(loginParam.getUsername());
            LoginUser loginUser = loginUserService.saveUser(blogUser, token);
            redisUtils.setCacheObject(Constants.LOGIN_TOKEN_KEY+ UUID.randomUUID(),loginUser,360, TimeUnit.SECONDS);


        } catch (Exception e) {
            e.printStackTrace();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(userDetails.getUsername(), Constants.LOGIN_SUCCESS,
                "登录成功"));
        return new BaseResult(CodeStatus.OK, "登录成功", result);
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResult logout(HttpServletRequest request){

        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        BlogUser blogUser = blogUserService.selectByAccount(account);
        String userName = blogUser.getUserName();

        // 获取 token
        String[] strings = request.getHeader("Authorization").split(" ");
       String token = strings[1];
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        // 记录用户退出日志
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        return BaseResult.success("用户已注销",CodeStatus.OK);
    }
}
