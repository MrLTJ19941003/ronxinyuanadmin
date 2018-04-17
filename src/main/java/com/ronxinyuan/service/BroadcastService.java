package com.ronxinyuan.service;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.Content;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 13045 on 2018/4/9.
 */
public interface BroadcastService {
    public AdminResult getDate(HttpServletRequest request);

    public Map add(HttpServletRequest request);

    public Content query(int id);

    public Map delete(int[] ids);

}
