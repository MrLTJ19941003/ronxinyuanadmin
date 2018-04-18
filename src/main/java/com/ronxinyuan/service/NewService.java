package com.ronxinyuan.service;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.News;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 13045 on 2018/3/26.
 */
public interface NewService {
    public AdminResult getDate(HttpServletRequest request);

    public News query(int id);

    public Map delete(int[] ids);

    public Map save(HttpServletRequest request);
}
