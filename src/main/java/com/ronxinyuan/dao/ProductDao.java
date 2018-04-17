package com.ronxinyuan.dao;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.Page;
import com.ronxinyuan.bean.Product;

import java.util.Map;

/**
 * Created by 13045 on 2018/2/23.
 */
public interface ProductDao {
    public Map save(Product product);

    public AdminResult getDate(Page page, Product product);

    public Map edit(Product content);

    public Map delete(int[] ids);

    public Product query(int id);
}
