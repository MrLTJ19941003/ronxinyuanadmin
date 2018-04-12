package com.ronxinyuan.service;

import com.ronxinyuan.bean.AdminResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 13045 on 2018/4/9.
 */
public interface BroadcastService {
    public AdminResult getDate(HttpServletRequest request);
}
