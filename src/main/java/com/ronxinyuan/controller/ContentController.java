package com.ronxinyuan.controller;

import com.ronxinyuan.bean.Content;
import com.ronxinyuan.bean.Result;
import com.ronxinyuan.common.ResultUtil;
import com.ronxinyuan.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 13045 on 2018/2/23.
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;
    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result addContent(@Valid Content content, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return null;
        }
        return ResultUtil.success(contentService.save(content));
    }
}
