package com.pzhu.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * @Author Guo Huaijian
 * @Date 2019/12/28 15:33
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.pzhu.myblog.project.**.mapper")
public class MyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
        System.out.println("                                           \n" +
                "    //   ) )                               \n" +
                "   ((      __  ___  ___      __    __  ___ \n" +
                "     \\\\     / /   //   ) ) //  ) )  / /    \n" +
                "       ) ) / /   //   / / //       / /     \n" +
                "((___ / / / /   ((___( ( //       / /    博客启动成功");
    }

}
