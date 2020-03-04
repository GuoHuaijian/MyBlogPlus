package com.pzhu.myblog.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author Guo Huaijian
 * @Date 2020/2/20 13:43
 */
@Configuration
public class WebMvcConfig  extends WebMvcConfigurationSupport {
    @Bean
    public FormContentFilter httpPutFormContentFilter() {
        return new FormContentFilter();
    }
}
