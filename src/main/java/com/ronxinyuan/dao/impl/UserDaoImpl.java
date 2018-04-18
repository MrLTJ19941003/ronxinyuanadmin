package com.ronxinyuan.dao.impl;

import com.ronxinyuan.bean.User;
import com.ronxinyuan.common.MD5Util;
import com.ronxinyuan.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by liutj on 2018/2/23.
 */
@Repository
public class UserDaoImpl implements UserDao{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User login(User user) {
        try{
            String sql = "select id,username,password,numberss,email,status from user where username=? or email=?";
            Object[] args = new Object[] {user.getUsername(),user.getUsername()};
            Object use = jdbcTemplate.queryForObject(sql, args,new UserRowMapper());
            if(use!=null){
                User u = (User)use;
                if(u.getPassword().equals(user.getPassword())){
                    return u;
                }
            }
            return null;
        }catch (Exception e){
            logger.error("出错，原因："+e);
            e.printStackTrace();
            return null;
        }
    }
}

/**
 * 行映射
 */
class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User news=new User();
        news.setId(resultSet.getInt("id"));
        news.setUsername(resultSet.getString("username"));
        news.setPassword(resultSet.getString("password"));
        news.setEmail(resultSet.getString("email"));
        news.setNumber(resultSet.getString("numberss"));
        news.setStatus(resultSet.getInt("status"));
        return news;
    }



}
