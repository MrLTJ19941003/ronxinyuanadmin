package com.ronxinyuan.controller;

import com.ronxinyuan.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Map;

/**
 * Created by 13045 on 2018/4/12.
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping("/images")
    public Map uploadImages(@RequestParam(value="file") MultipartFile[] files){
        return fileUploadService.uploadImages(files);
    }
}
