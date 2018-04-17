package com.ronxinyuan.dao;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.Content;
import com.ronxinyuan.bean.News;
import com.ronxinyuan.bean.Page;

import java.util.Map;

/**
 * Created by liutj on 2018/4/9.
 */
public interface BroadcastDao {
    public AdminResult getDate(Page page, Content content);

    public Map add(Content content);

    public Map delete(int[] ids);

    public Map edit(Content content);

    public Content query(int id);
}
