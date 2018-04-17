package com.ronxinyuan.controller;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.Content;
import com.ronxinyuan.bean.Result;
import com.ronxinyuan.common.ResultUtil;
import com.ronxinyuan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by liutj on 2018/2/23.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询
     */
    @RequestMapping(value = "/getDate",method = RequestMethod.GET)
    @ResponseBody
    public AdminResult getNewsContent(HttpServletRequest request){
        return productService.getDate(request);
    }
    /**
     * 新增
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Map addProduct(HttpServletRequest request){
        return productService.save(request);
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Map editProduct(HttpServletRequest request){
        return productService.save(request);
    }
}
