package com.ronxinyuan.controller;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.Content;
import com.ronxinyuan.bean.Product;
import com.ronxinyuan.bean.Result;
import com.ronxinyuan.common.ResultUtil;
import com.ronxinyuan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by liutj on 2018/2/23.
 */
@Controller
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
     * 根据ID查询
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String queryByID(@RequestParam(value="id") int id, ModelMap map){
        Product product = productService.query(id);
        map.addAttribute("product",product);
        return "main/productEdit";//broadcastService.add();
    }
    /**
     * 新增
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map addProduct(HttpServletRequest request){
        return productService.save(request);
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map editProduct(HttpServletRequest request){
        return productService.save(request);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public Map delete(@RequestParam(value="ids[]") int[] ids){
        return productService.delete(ids);//broadcastService.add();
    }
}
