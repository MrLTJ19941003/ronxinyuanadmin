package com.ronxinyuan.service;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.Content;
import com.ronxinyuan.bean.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by liutj on 2018/2/23.
 */
public interface ProductService {
    public Map save(HttpServletRequest request);

    public AdminResult getDate(HttpServletRequest request);

    public Product query(int id);

    public Map delete(int[] ids);
}
