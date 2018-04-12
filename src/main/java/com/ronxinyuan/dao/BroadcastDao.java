package com.ronxinyuan.dao;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.News;
import com.ronxinyuan.bean.Page;

/**
 * Created by 13045 on 2018/4/9.
 */
public interface BroadcastDao {
    public AdminResult getDate(Page page, News news);
}
