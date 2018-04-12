package com.ronxinyuan.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Map;

/**
 * Created by liutj on 2018/4/12.
 */
public interface FileUploadService {
    public Map uploadImages(MultipartFile[] file);
}
