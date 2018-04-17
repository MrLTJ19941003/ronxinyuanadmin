package com.ronxinyuan.service.impl;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.Content;
import com.ronxinyuan.bean.News;
import com.ronxinyuan.bean.Page;
import com.ronxinyuan.common.StringUtils;
import com.ronxinyuan.dao.BroadcastDao;
import com.ronxinyuan.service.BroadcastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13045 on 2018/4/9.
 */
@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BroadcastDao broadcastDao;

    @Override
    public AdminResult getDate(HttpServletRequest request) {
        Page page=new Page();
        Content content = new Content();
        int limit,start,pageIndex,id;
        String title,contentTime;
        try {
            limit = StringUtils.MyparseInt(request.getParameter("limit"));
            start = StringUtils.MyparseInt(request.getParameter("start"));
            pageIndex = StringUtils.MyparseInt(request.getParameter("pageIndex"));
            String ids = request.getParameter("id");
            if(ids!=null && ids!=""){
                id = StringUtils.MyparseInt(ids);
                content.setId(id);
            }else{
                content.setId(-1);
            }
            title = request.getParameter("title");
            contentTime = request.getParameter("contentTime");
            page.setStart(start);
            page.setLimit(limit);
            page.setPageIndex(pageIndex);

            content.setTitle(title);
            content.setCreateTime(contentTime);
        }catch (Exception e){
            return new AdminResult(true,"参数有误"+e.toString());
        }

        return broadcastDao.getDate(page, content);
    }

    /**
     * 广播新增
     * @param request
     * @return
     */
    @Override
    public Map add(HttpServletRequest request) {
        Map map =new HashMap();
        Content content = new Content();
        try {
            String id=request.getParameter("id");
            String title = request.getParameter("title");
            String  constr= request.getParameter("editorHidden");
            String  date= request.getParameter("date");
            int type = StringUtils.MyparseInt(request.getParameter("type"));
            if(StringUtils.isEmpty(title) && StringUtils.isEmpty(date) && StringUtils.isEmpty(constr)){
                throw new Exception("参数不正确");
            }
            content.setContent(constr);
            content.setTitle(title);
            content.setCreateTime(date);
            content.setStatus(type);
            logger.debug("add参数："+content);
            if(StringUtils.isEmpty(id)){//判断是新增还是修改
                return broadcastDao.add(content);
            }else{
                content.setId(StringUtils.MyparseInt(id));
                return broadcastDao.edit(content);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("add错误："+e);
            map.put("status","false");
            map.put("error",e);
            return map;
        }


    }

    @Override
    public Content query(int id) {
        return broadcastDao.query(id);
    }

    @Override
    public Map delete(int[] ids) {
        return broadcastDao.delete(ids);
    }

}
