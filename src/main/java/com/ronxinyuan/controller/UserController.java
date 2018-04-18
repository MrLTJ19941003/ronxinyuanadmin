package com.ronxinyuan.controller;

import com.ronxinyuan.bean.User;
import com.ronxinyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by 13045 on 2018/2/23.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/loginout", method= RequestMethod.GET)
    public String toLoginout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String toLogin( ModelMap modelMap)
    {
        modelMap.addAttribute("error","");
        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String toLogin(HttpServletRequest request, ModelMap modelMap)
    {

        String result = userService.login(request);
        if(result == "-1"){
            return "index";
        }
        modelMap.addAttribute("error",result);
        return "login";

    }
}
