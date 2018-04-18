package com.ronxinyuan.dao.impl;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.News;
import com.ronxinyuan.bean.Page;
import com.ronxinyuan.common.StringUtils;
import com.ronxinyuan.dao.NewsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13045 on 2018/3/26.
 */
@Repository
public class NewsDaoImpl implements NewsDao{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

            List <Object> queryList=new ArrayList<Object>();
            StringBuffer sql = new StringBuffer(" select id,news_name,news_content,news_time,news_url,news_status from news where 1=1 ");
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

            List<News> newsList=jdbcTemplate.query(sql.toString(),new NewsRowMapper(),queryList.toArray());
            adminResult.setRows(newsList);
        }catch (Exception e){
            logger.error("出错，原因："+e);
            e.printStackTrace();
            return new AdminResult(true,"参数有误"+e.toString());
        }

        return adminResult;
    }

    @Override
    public Map save(News news) {
        Map map = new HashMap();
        try{
            jdbcTemplate.update(new PreparedStatementCreator(){
                                    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                                        String sql="insert into news (news_name,news_time,news_url,news_status)  values(?,?,?,?)";
                                        PreparedStatement ps=conn.prepareStatement(sql);
                                        ps.setString(1,news.getNewsName());
                                        ps.setString(2,news.getNewsTime());
                                        ps.setString(3,news.getNewsUrl());
                                        ps.setInt(4,news.getNewsStatus());
                                        return ps;
                                    }
                                }
            );
            map.put("status","true");
        }catch (Exception e){
            logger.error("出错，原因："+e);
            e.printStackTrace();
            map.put("status","false");
            map.put("error",e);
            return map;
        }
        return map;
    }

    @Override
    public Map edit(News news) {
        Map map = new HashMap();
        try{
            jdbcTemplate.update(new PreparedStatementCreator(){
                                    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                                        String sql="update news set news_name=?,news_time=?,news_url=?,news_status=? where id=? ";
                                        PreparedStatement ps=conn.prepareStatement(sql);
                                        ps.setString(1,news.getNewsName());
                                        ps.setString(2,news.getNewsTime());
                                        ps.setString(3,news.getNewsUrl());
                                        ps.setInt(4,news.getNewsStatus());
                                        ps.setInt(5,news.getId());
                                        return ps;
                                    }
                                }
            );
            map.put("status","true");
        }catch (Exception e){
            logger.error("出错，原因："+e);
            e.printStackTrace();
            map.put("status","false");
            map.put("error",e);
            return map;
        }
        return map;
    }

    @Override
    public Map delete(int[] ids) {
        Map map = new HashMap();
        try{
            List<Object[]> batchArgs=new ArrayList<Object[]>();
            for(int i=0;i<ids.length;i++){
                batchArgs.add(new Object[]{i+1,ids[i]});
            }
            String sql = "DELETE from news where id=?";
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    int id = ids[i];
                    ps.setInt(1,id);
                }
                @Override
                public int getBatchSize() {
                    return ids.length;
                }
            });
            map.put("status","true");
        }catch (Exception e){
            logger.error("出错，原因："+e);
            e.printStackTrace();
            map.put("status","false");
            map.put("error",e);
            return map;
        }
        return map;
    }

    @Override
    public News query(int id) {
        try{
            String sql = "select id,news_name,news_content,news_time,news_url,news_status from news where id=?";
            Object[] args = new Object[] {id};
            Object use = jdbcTemplate.queryForObject(sql, args,new NewsRowMapper());
            return (News)use;
        }catch (Exception e){
            logger.error("出错，原因："+e);
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取记录数
     * @return
     * @throws Exception
     */
    public Integer getCount(String str,List<Object> list) throws  Exception{
        StringBuffer sql = new StringBuffer(" select count(id) from news where 1=1 ");
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
class NewsRowMapper implements RowMapper<News> {

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
