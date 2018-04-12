package com.ronxinyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 13045 on 2018/2/23.
 */
@Controller
public class goPageController {
    @RequestMapping("/")
    public String toIndex()
    {
        return "index";
    }

    @RequestMapping("/main/{page}")
    public String toPage(@PathVariable("page") String page)
    {
        return "main/"+page;
    }
}
