package com.ronxinyuan.service.impl;

import com.ronxinyuan.common.FtpFileUtil;
import com.ronxinyuan.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liutj on 2018/4/12.
 */
@Service
public class FileUploadServiceImpl implements FileUploadService{

    @Value("${imageServerUrl}")
    private String imageServerUrl;

    @Override
    public Map uploadImages(MultipartFile[] files) {
        Map map = new HashMap();
        InputStream inputStream = null;

        String[] strings = new String[files.length];
        String[] imagStrs = new String[files.length];
        InputStream[] inputStreams = new InputStream[files.length];
        try {
            for (int i =0 ;i<files.length;i++){
                MultipartFile file = files[i];
                inputStreams[i] = file.getInputStream();
                imagStrs[i]=file.getOriginalFilename();
                strings[i] = imageServerUrl+file.getOriginalFilename();
            }
            boolean b = FtpFileUtil.uploadFile(imagStrs,inputStreams);
            if(b){
                map.put("errno","0");
                map.put("data",strings);
            }else{
                map.put("errno","1");
            }

        } catch (IOException e) {
            map.put("errno","1");
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map uploadfile(MultipartFile file) {
        Map map = new HashMap();
        InputStream inputStream = null;

        String[] strings = new String[1];
        String[] imagStrs = new String[1];
        InputStream[] inputStreams = new InputStream[1];
        try {
            for (int i =0 ;i<1;i++){
                inputStreams[i] = file.getInputStream();
                imagStrs[i]=file.getOriginalFilename();
                strings[i] = imageServerUrl+file.getOriginalFilename();
            }
            boolean b = FtpFileUtil.uploadFile(imagStrs,inputStreams);
            if(b){
                map.put("url",strings);
            }else{
            }
        } catch (IOException e) {
            map.put("errno","1");
            e.printStackTrace();
        }
        return map;
    }
}
