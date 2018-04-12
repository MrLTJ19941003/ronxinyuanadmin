package com.ronxinyuan.controller;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.service.BroadcastService;
import com.ronxinyuan.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liutj on 2018/4/9.
 */
@RequestMapping(value="/broadcast")
@Controller
public class BroadcastController {
    @Autowired
    private BroadcastService broadcastService;
    /**
     * 查询
     */
    @RequestMapping(value = "/getDate",method = RequestMethod.GET)
    @ResponseBody
    public AdminResult getNewsContent(HttpServletRequest request){
        return broadcastService.getDate(request);
    }
    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String queryByID(@RequestParam(value="id") int id, ModelMap map){
        map.addAttribute("broadcastEdit",null);
        return "/main/broadcastEdit";//broadcastService.add();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public AdminResult edit(HttpServletRequest request){
        return null;//broadcastService.add();
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public AdminResult add(HttpServletRequest request){
        return null;//broadcastService.add();
    }
}
