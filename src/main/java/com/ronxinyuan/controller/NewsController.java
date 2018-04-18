package com.ronxinyuan.controller;

import com.ronxinyuan.bean.*;
import com.ronxinyuan.common.ResultUtil;
import com.ronxinyuan.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by liutj on 2018/3/26.
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

    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String queryByID(@RequestParam(value="id") int id, ModelMap map){
        News news = newService.query(id);
        map.addAttribute("news",news);
        return "main/newsEdit";//broadcastService.add();
    }
    /**
     * 新增
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map addProduct(HttpServletRequest request){
        return newService.save(request);
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map editProduct(HttpServletRequest request){
        return newService.save(request);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public Map delete(@RequestParam(value="ids[]") int[] ids){
        return newService.delete(ids);//broadcastService.add();
    }
}
