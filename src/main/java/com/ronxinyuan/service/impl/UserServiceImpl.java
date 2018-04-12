package com.ronxinyuan.service.impl;

import com.ronxinyuan.bean.User;
import com.ronxinyuan.dao.UserDao;
import com.ronxinyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 13045 on 2018/2/23.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public int login(User user) {
        return userDao.login(user);
    }
}
