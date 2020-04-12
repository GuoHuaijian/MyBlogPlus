package com.pzhu.myblog.blogTest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.pzhu.myblog.common.utils.http.HttpUtils;
import com.pzhu.myblog.project.blog.domain.BlogCategory;
import com.pzhu.myblog.project.blog.dto.BlogArticleDTO;
import com.pzhu.myblog.project.blog.mapper.BlogArticleMapper;
import com.pzhu.myblog.project.blog.mapper.BlogTagMapper;
import com.pzhu.myblog.project.blog.service.BlogArticleService;
import com.pzhu.myblog.project.blog.service.BlogCategoryService;
import com.pzhu.myblog.project.blog.service.BlogTagService;
import com.pzhu.myblog.project.blog.vo.BlogArticleVO;
import com.pzhu.myblog.project.blog.vo.TagDetail;
import com.pzhu.myblog.project.frontend.domain.Archive;
import com.pzhu.myblog.project.frontend.service.FrontendService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Guo Huaijian
 * @Date 2019/12/28 16:23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class testBlog {

    @Autowired(required = false)
    private BlogArticleMapper blogArticleMapper;

    @Autowired
    private BlogArticleService blogArticleService;

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private BlogCategoryService blogCategoryService;

    @Autowired
    private FrontendService frontendService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void testSelect(){
        log.info("开始查询");
        BlogArticleDTO blogArticleDTO = new BlogArticleDTO();
        blogArticleDTO.setStatus("已发布");
        blogArticleDTO.setTitle("一个标题2");
        blogArticleDTO.setCategory("spring");
        ArrayList<String> list = new ArrayList<>();
        list.add("标签1");
        list.add("标签2");
        blogArticleDTO.setTag(list);
        blogArticleDTO.setAuthor("admin");
        blogArticleDTO.setSummary("简介帆帆帆帆帆帆帆帆");
        blogArticleDTO.setSummary_img("www.guohuaijian.com");
        blogArticleDTO.setContent("正文烦烦烦烦烦烦烦烦烦烦烦烦");
        int i = blogArticleService.addArticle(blogArticleDTO);
        log.info("查询结束");
        System.out.println(i);
    }

    @Test
    public void testSelectPage(){
        log.info("开始查询");
        Page<BlogArticleVO> page = new Page<>(1,10);
        Page<BlogArticleVO> blogArticleVOPage = blogArticleService.selectPageVo(page);
        log.info("查询结束");
        System.out.println(blogArticleVOPage.toString());
    }
    @Test
    public void testtag(){
        log.info("开始查询");
        List<String> name = new ArrayList<>();
        name.add("标签1");
        name.add("标签2");
        List<Integer> integers = blogTagMapper.selectByName(name);
        log.info("查询结束");
        System.out.println(integers);
    }

    @Test
    public void testag(){
        log.info("开始查询");
        BlogCategory spring = blogCategoryService.selectByName("spring");
        log.info("查询结束");
        System.out.println(spring);
    }

    @Test
    public void testag1(){
        log.info("开始查询");
        TagDetail tagDetail = blogTagService.selectContact(1);
        log.info("查询结束");
        System.out.println(tagDetail);
    }

    @Test
    public void  testHttp() throws Exception {
        Map<String, String> params = Maps.newHashMap();
        params.put("ip=","101.206.167.17");
        String sendPost = HttpUtils.sendPost("http://ip.taobao.com/service/getIpInfo.php", "ip=" + "101.206.167.17");
        JSONObject obj = JSONObject.parseObject(sendPost);
        System.out.println(obj.toString());
    }

    @Test
    public void  testArchive(){
        List<Archive> archives = frontendService.archiveList();
        System.out.println(archives);
    }

    @Test
    public void  mima(){
        System.out.println(passwordEncoder.encode("123456"));
    }
}
