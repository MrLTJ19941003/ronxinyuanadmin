package com.ronxinyuan.controller;

import com.ronxinyuan.bean.User;
import com.ronxinyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 13045 on 2018/2/23.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String toLogin()
    {
        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String toLogin(@RequestParam String username,@RequestParam String password)
    {
        User uesr = new User();
        uesr.setUsername(username);
        uesr.setPassword(password);
        int result = userService.login(uesr);
        if(result>0){
            return "/index";
        }
        return "/login";

    }
}
