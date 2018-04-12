package com.ronxinyuan.controller;

import com.ronxinyuan.bean.*;
import com.ronxinyuan.common.ResultUtil;
import com.ronxinyuan.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by 13045 on 2018/3/26.
 * 新闻模块
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewService newService;
    /**
     * 查询
     */
    @RequestMapping(value = "/getDate",method = RequestMethod.GET)
    @ResponseBody
    public AdminResult getNewsContent(HttpServletRequest request){
        return newService.getDate(request);
    }
}
