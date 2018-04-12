package com.ronxinyuan.service.impl;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.News;
import com.ronxinyuan.bean.Page;
import com.ronxinyuan.dao.BroadcastDao;
import com.ronxinyuan.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 13045 on 2018/4/9.
 */
@Service
public class BroadcastServiceImpl implements BroadcastService {

    @Autowired
    BroadcastDao broadcastDao;

    @Override
    public AdminResult getDate(HttpServletRequest request) {
        Page page=new Page();
        News news = new News();
        int limit,start,pageIndex,id;
        String newsName,newsTime;
        try {
            limit = MyparseInt(request.getParameter("limit"));
            start = MyparseInt(request.getParameter("start"));
            pageIndex = MyparseInt(request.getParameter("pageIndex"));
            String ids = request.getParameter("id");
            if(ids!=null && ids!=""){
                id = MyparseInt(ids);
                news.setId(id);
            }else{
                news.setId(-1);
            }
            newsName = request.getParameter("newsName");
            newsTime = request.getParameter("newsTime");
            page.setStart(start);
            page.setLimit(limit);
            page.setPageIndex(pageIndex);

            news.setNewsName(newsName);
            news.setNewsTime(newsTime);

        }catch (Exception e){
            return new AdminResult(true,"参数有误"+e.toString());
        }

        return broadcastDao.getDate(page, news);
    }

    private int MyparseInt(String string)throws Exception{
        try{
            return Integer.parseInt(string);
        }catch (NumberFormatException e){
            throw e;
        }catch (NullPointerException e){
            throw e;
        }

    }
}
