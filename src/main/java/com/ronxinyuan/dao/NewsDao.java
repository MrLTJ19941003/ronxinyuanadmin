package com.ronxinyuan.dao;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.News;
import com.ronxinyuan.bean.Page;


/**
 * Created by 13045 on 2018/3/26.
 */
public interface NewsDao {
    public AdminResult getDate(Page page, News news);
}
