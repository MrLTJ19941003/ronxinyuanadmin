package com.ronxinyuan.dao.impl;

import com.ronxinyuan.bean.Content;
import com.ronxinyuan.dao.ContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by 13045 on 2018/2/23.
 */
@Repository
public class ContentDaoImpl implements ContentDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int save(Content content) {
        return 0;
    }
}
