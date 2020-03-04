package com.pzhu.myblog.project.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author Guo Huaijian
 * @Date 2020/1/14 17:54
 */
public interface FileUploadService {

    /**
     * 上传文件到七牛云
     * @param file
     * @return 返回文件路径
     */
    String uploadFileToQiNiu(MultipartFile file);
}
