package com.ronxinyuan.service.impl;

import com.ronxinyuan.bean.User;
import com.ronxinyuan.common.MD5Util;
import com.ronxinyuan.common.StringUtils;
import com.ronxinyuan.dao.UserDao;
import com.ronxinyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 13045 on 2018/2/23.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public String login(HttpServletRequest request) {
        String result="";
        User user = new User();
        String username= request.getParameter("username");
        String password= request.getParameter("password");
        HttpSession session = request.getSession(true);
        if(StringUtils.isEmpty(username) && StringUtils.isEmpty(password)){
            result = "参数为空";
        }
        user.setUsername(username);
        try {
            user.setPassword(MD5Util.EncoderByMd5(password));
            User user1 = userDao.login(user);
            if(user1 != null){
                session.setAttribute("user",user1);
                result="-1";
            }else{
                result = "用户名或密码不正确";
            }
        } catch (Exception e) {
            result = "有异常错误";
            e.printStackTrace();
        }
        return result;
    }
}
