package com.pzhu.myblog.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 七牛云配置
 *
 * @Author Guo Huaijian
 * @Date 2020/1/12 21:18
 */
@Data
@Component
@ConfigurationProperties(prefix = "qiniuyun")
public class QiNiuConfig {

    /**
     * 需要操作的账号的AK
     */
    private String accessKey;
    /**
     * 需要操作的账号的SK
     */
    private String secretKey;
    /**
     * 要上传的空间名称
     */
    private String bucket;
    /**
     *图片访问路径前缀
     */
    private String path;
    private Long imageMaxSize;
    private Long imageMaxNameLength;
}

