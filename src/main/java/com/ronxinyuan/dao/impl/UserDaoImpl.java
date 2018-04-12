package com.ronxinyuan.dao.impl;

import com.ronxinyuan.bean.User;
import com.ronxinyuan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by 13045 on 2018/2/23.
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int login(User user) {
        return 0;
    }
}
