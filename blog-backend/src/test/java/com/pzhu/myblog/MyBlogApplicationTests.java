package com.pzhu.myblog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Guo Huaijian
 * @Date 2019/12/28 15:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(new BCryptPasswordEncoder().encode("111111"));
    }

}
