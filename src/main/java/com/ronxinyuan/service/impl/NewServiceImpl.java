package com.ronxinyuan.service.impl;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.News;
import com.ronxinyuan.bean.Page;
import com.ronxinyuan.common.StringUtils;
import com.ronxinyuan.dao.NewsDao;
import com.ronxinyuan.service.NewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liutj on 2018/3/26.
 */
@Service
public class NewServiceImpl implements NewService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NewsDao newsDao;

    /**
     * 查询全部数据
     * @param request
     * @return
     */
    @Override
    public AdminResult getDate(HttpServletRequest request) {
        Page page=new Page();
        News news = new News();
        int limit,start,pageIndex,id;
        String newsName,newsTime;
        try {
            limit = StringUtils.MyparseInt(request.getParameter("limit"));
            start = StringUtils.MyparseInt(request.getParameter("start"));
            pageIndex = StringUtils.MyparseInt(request.getParameter("pageIndex"));
            String ids = request.getParameter("id");
            if(ids!=null && ids!=""){
                id = StringUtils.MyparseInt(ids);
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

        return newsDao.getDate(page, news);
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public News query(int id) {
        return newsDao.query(id);
    }

    /**
     * 根据ID删除
     * @param ids
     * @return
     */
    @Override
    public Map delete(int[] ids) {
        return newsDao.delete(ids);
    }

    /**
     * 新增、编辑
     * @param request
     * @return
     */
    @Override
    public Map save(HttpServletRequest request) {
        Map map =new HashMap();
        News news = new News();
        try {
            String id=request.getParameter("id");
            String newsName = request.getParameter("newsTitle");
            int  newsStatus= StringUtils.MyparseInt(request.getParameter("newsStatus"));
            String  newsTime= request.getParameter("newsTime");
            String  newsUrl= request.getParameter("newsUrl");
            if(StringUtils.isEmpty(newsName) && StringUtils.isEmpty(newsTime) && StringUtils.isEmpty(newsUrl)){
                throw new Exception("参数不正确");
            }
            news.setNewsName(newsName);
            news.setNewsStatus(newsStatus);
            news.setNewsTime(newsTime);
            news.setNewsUrl(newsUrl);
            logger.debug("add参数："+news);
            if(StringUtils.isEmpty(id)){//判断是新增还是修改
                return newsDao.save(news);
            }else{
                news.setId(StringUtils.MyparseInt(id));
                return newsDao.edit(news);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("add错误："+e);
            map.put("status","false");
            map.put("error",e);
            return map;
        }
    }
}
