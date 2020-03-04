package com.pzhu.myblog.project.file.service.impl;

import com.pzhu.myblog.framework.upload.QiNiuUtils;
import com.pzhu.myblog.project.file.domain.FileItemInfo;
import com.pzhu.myblog.project.file.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * 文件上传
 *
 * @Author Guo Huaijian
 * @Date 2020/1/14 17:55
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    /**
     * 上传文件到七牛云
     * @param file
     * @return 返回文件路径
     */
    @Override
    public String uploadFileToQiNiu(MultipartFile file) {
        Optional<FileItemInfo> fileItemInfo = QiNiuUtils.uploadFile(file);
        String path = fileItemInfo.get().getPath();
        return path;
    }
}
