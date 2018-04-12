package com.ronxinyuan.service.impl;

import com.ronxinyuan.bean.Content;
import com.ronxinyuan.dao.ContentDao;
import com.ronxinyuan.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 13045 on 2018/2/23.
 */
@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private ContentDao contentDao;

    @Override
    public int save(Content content) {
        return contentDao.save(content);
    }
}
