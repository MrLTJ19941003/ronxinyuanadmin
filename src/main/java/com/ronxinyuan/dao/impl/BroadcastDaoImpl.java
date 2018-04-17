package com.ronxinyuan.dao.impl;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.Content;
import com.ronxinyuan.bean.News;
import com.ronxinyuan.bean.Page;
import com.ronxinyuan.common.StringUtils;
import com.ronxinyuan.dao.BroadcastDao;
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
 * Created by 13045 on 2018/4/9.
 */
@Repository
public class BroadcastDaoImpl implements BroadcastDao {

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
    public AdminResult getDate(Page page, Content content) {
        AdminResult adminResult = new AdminResult();
        try{

            List<Object> queryList=new ArrayList<Object>();
            StringBuffer sql = new StringBuffer(" select id,title,url,content,createTime,STATUS from radiobroadcast where 1=1 ");
            StringBuffer wheresql = new StringBuffer("");
            if(content.getId() != -1){
                wheresql.append(" and id = ? ");
                queryList.add(content.getId());
            }

            if(!StringUtils.isEmpty(content.getTitle())){
                wheresql.append(" and title like ? ");
                queryList.add("%"+content.getTitle()+"%");
            }

            if(!StringUtils.isEmpty(content.getCreateTime())){
                wheresql.append(" and createTime = ? ");
                queryList.add(content.getCreateTime());
            }
            adminResult.setResults(getCount(wheresql.toString(),queryList));
            sql.append(wheresql);
            if(page != null){
                sql.append(" limit ?,? ");
                queryList.add(page.getStart());
                queryList.add(page.getLimit());
            }

            List<Content> contentList=jdbcTemplate.query(sql.toString(),new BroadcastRowMapper(),queryList.toArray());
            adminResult.setRows(contentList);
        }catch (Exception e){
            logger.error("出错，原因："+e);
            return new AdminResult(true,"参数有误"+e.toString());
        }

        return adminResult;
    }

    @Override
    public Map add(Content content) {
        Map map = new HashMap();
        try{
            jdbcTemplate.update(new PreparedStatementCreator(){
                                    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                                        String sql="insert into radiobroadcast (title,url,content,createTime,STATUS)  values(?,?,?,?,?)";
                                        PreparedStatement ps=conn.prepareStatement(sql);
                                        ps.setString(1,content.getTitle());
                                        ps.setString(2,content.getUrl());
                                        ps.setString(3,content.getContent());
                                        ps.setString(4,content.getCreateTime());
                                        ps.setInt(5,content.getStatus());
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
    public Map edit(Content content) {
        Map map = new HashMap();
        try{
            jdbcTemplate.update(new PreparedStatementCreator(){
                                    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                                        String sql="update radiobroadcast set title=?,url=?,content=?,createTime=?,STATUS=? where id=?";
                                        PreparedStatement ps=conn.prepareStatement(sql);
                                        ps.setString(1,content.getTitle());
                                        ps.setString(2,content.getUrl());
                                        ps.setString(3,content.getContent());
                                        ps.setString(4,content.getCreateTime());
                                        ps.setInt(5,content.getStatus());
                                        ps.setInt(6,content.getId());
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
            String sql = "DELETE from radiobroadcast where id=?";
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    int id = ids[i];
                    ps.setInt(1,id);
                }
                @Override
                public int getBatchSize() {
                    // TODO Auto-generated method stub
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

    /**
     *根据 ID查询
     * @param id
     * @return
     */
    @Override
    public Content query(int id) {
        String sql = "select id,title,url,content,createTime,STATUS from radiobroadcast where id=?";
        Object[] args = new Object[] {id};
        Object use = jdbcTemplate.queryForObject(sql, args,new BroadcastRowMapper());
        return (Content)use;
    }

    /**
     * 获取记录数
     * @return
     * @throws Exception
     */
    public Integer getCount(String str,List<Object> list) throws  Exception{
        StringBuffer sql = new StringBuffer(" select count(id) from radiobroadcast where 1=1 ");
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
class BroadcastRowMapper implements RowMapper<Content> {

    @Override
    public Content mapRow(ResultSet resultSet, int i) throws SQLException {
        Content content=new Content();
        content.setId(resultSet.getInt("id"));
        content.setTitle(resultSet.getString("title"));
        content.setCreateTime(resultSet.getString("createTime"));
        content.setContent(resultSet.getString("content"));
        content.setStatus(Integer.parseInt(resultSet.getString("STATUS")));
        return content;
    }



}
