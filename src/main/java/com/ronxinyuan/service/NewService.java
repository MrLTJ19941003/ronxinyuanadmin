package com.ronxinyuan.service;

import com.ronxinyuan.bean.AdminResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 13045 on 2018/3/26.
 */
public interface NewService {
    public AdminResult getDate(HttpServletRequest request);
}
