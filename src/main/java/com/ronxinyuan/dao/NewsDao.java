package com.ronxinyuan.dao;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.News;
import com.ronxinyuan.bean.Page;

import java.util.Map;


/**
 * Created by 13045 on 2018/3/26.
 */
public interface NewsDao {
    public AdminResult getDate(Page page, News news);

    public Map save(News news);

    public Map edit(News news);

    public Map delete(int[] ids);

    public News query(int id);
}
