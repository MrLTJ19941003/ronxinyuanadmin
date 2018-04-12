package com.ronxinyuan.dao.impl;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.News;
import com.ronxinyuan.bean.Page;
import com.ronxinyuan.common.StringUtils;
import com.ronxinyuan.dao.BroadcastDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13045 on 2018/4/9.
 */
@Repository
public class BroadcastDaoImpl implements BroadcastDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取用户列表
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public AdminResult getDate(Page page, News news) {
        AdminResult adminResult = new AdminResult();
        try{

            List<Object> queryList=new ArrayList<Object>();
            StringBuffer sql = new StringBuffer(" select id,news_name,news_content,news_time,news_url,news_status from news where 1=1 and news_status = 0 ");
            StringBuffer wheresql = new StringBuffer("");
            if(news.getId() != -1){
                wheresql.append(" and id = ? ");
                queryList.add(news.getId());
            }

            if(!StringUtils.isEmpty(news.getNewsName())){
                wheresql.append(" and news_name like ? ");
                queryList.add("%"+news.getNewsName()+"%");
            }

            if(!StringUtils.isEmpty(news.getNewsTime())){
                wheresql.append(" and news_time = ? ");
                queryList.add(news.getNewsTime());
            }
            adminResult.setResults(getCount(wheresql.toString(),queryList));
            sql.append(wheresql);
            if(page != null){
                sql.append(" limit ?,? ");
                queryList.add(page.getStart());
                queryList.add(page.getLimit());
            }

            List<News> newsList=jdbcTemplate.query(sql.toString(),new BroadcastRowMapper(),queryList.toArray());
            adminResult.setRows(newsList);
        }catch (Exception e){
            return new AdminResult(true,"参数有误"+e.toString());
        }

        return adminResult;
    }

    /**
     * 获取记录数
     * @return
     * @throws Exception
     */
    public Integer getCount(String str,List<Object> list) throws  Exception{
        StringBuffer sql = new StringBuffer(" select count(id) from news where 1=1 and news_status = 0 ");
        sql.append(str);
        //jdbcTemplate.getMaxRows();
        Integer total=jdbcTemplate.queryForObject(sql.toString(),list.toArray(),Integer.class);
        System.out.println("操作结果记录数：  "+total);
        return total;
    }
}

/**
 * 行映射
 */
class BroadcastRowMapper implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet resultSet, int i) throws SQLException {
        News news=new News();
        news.setId(resultSet.getInt("id"));
        news.setNewsName(resultSet.getString("news_name"));
        news.setNewsContent(resultSet.getString("news_content"));
        news.setNewsTime(resultSet.getString("news_time"));
        news.setNewsUrl(resultSet.getString("news_url"));
        news.setNewsStatus(resultSet.getInt("news_status"));
        return news;
    }



}
