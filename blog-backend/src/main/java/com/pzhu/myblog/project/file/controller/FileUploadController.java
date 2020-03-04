package com.pzhu.myblog.project.file.controller;

import com.pzhu.myblog.common.constant.CodeStatus;
import com.pzhu.myblog.common.dto.BaseResult;
import com.pzhu.myblog.project.file.service.FileUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @Author Guo Huaijian
 * @Date 2020/1/14 18:10
 */
@RestController
@RequestMapping("file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 上传文件到七牛云
     * @param file
     * @return
     */
    @PostMapping("upload")
    public BaseResult uploadFileToQiNiu(MultipartFile file) {
        if (StringUtils.isBlank(file.getOriginalFilename())){
            return BaseResult.error("上传文件失败请重新上传");
        } else {
            String path = fileUploadService.uploadFileToQiNiu(file);
            return BaseResult.success(CodeStatus.OK,path,"请求成功");
        }
    }
}
