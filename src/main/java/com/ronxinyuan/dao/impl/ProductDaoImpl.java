package com.ronxinyuan.dao.impl;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.Content;
import com.ronxinyuan.bean.Page;
import com.ronxinyuan.bean.Product;
import com.ronxinyuan.common.StringUtils;
import com.ronxinyuan.dao.ProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liutj on 2018/2/23.
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 新增
     * @param product
     * @return
     */
    @Override
    public Map save(Product product) {
        Map map = new HashMap();
        try{
            jdbcTemplate.update(new PreparedStatementCreator(){
                                    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                                        String sql="insert into product (product_name,product_content,product_imageurl,product_createtime,product_details,product_status,product_url)  values(?,?,?,?,?,?,?)";
                                        PreparedStatement ps=conn.prepareStatement(sql);
                                        ps.setString(1,product.getProductName());
                                        ps.setString(2,product.getProductContent());
                                        ps.setString(3,product.getProductImageurl());
                                        ps.setString(4,product.getProductCreatetime());
                                        ps.setString(5,product.getProductDetails());
                                        ps.setInt(6,product.getProductStatus());
                                        ps.setString(7,product.getProductUrl());
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

    /**
     * 查询全部数据
     * @param page
     * @param product
     * @return
     */
    @Override
    public AdminResult getDate(Page page, Product product) {
        AdminResult adminResult = new AdminResult();
        try{

            List<Object> queryList=new ArrayList<Object>();
            StringBuffer sql = new StringBuffer(" select id,product_name,product_content,product_createtime,product_details,product_imageurl,product_status,product_url from product where 1=1 ");
            StringBuffer wheresql = new StringBuffer("");
            if(product.getId() != -1){
                wheresql.append(" and id = ? ");
                queryList.add(product.getId());
            }

            if(!StringUtils.isEmpty(product.getProductName())){
                wheresql.append(" and product_name like ? ");
                queryList.add("%"+product.getProductName()+"%");
            }

            if(!StringUtils.isEmpty(product.getProductCreatetime())){
                wheresql.append(" and product_createtime = ? ");
                queryList.add(product.getProductCreatetime());
            }

            if(!StringUtils.isEmpty(product.getProductDetails())){
                wheresql.append(" and product_details like ? ");
                queryList.add("%"+product.getProductDetails()+"%");
            }
            adminResult.setResults(getCount(wheresql.toString(),queryList));
            sql.append(wheresql);
            if(page != null){
                sql.append(" limit ?,? ");
                queryList.add(page.getStart());
                queryList.add(page.getLimit());
            }

            List<Product> productList=jdbcTemplate.query(sql.toString(),new ProductRowMapper(),queryList.toArray());
            adminResult.setRows(productList);
        }catch (Exception e){
            logger.error("出错，原因："+e);
            return new AdminResult(true,"参数有误"+e.toString());
        }

        return adminResult;
    }

    /**
     * 编辑
     * @param content
     * @return
     */
    @Override
    public Map edit(Product content) {
        return null;
    }

    /**
     * 根据ID删除
     * @param ids
     * @return
     */
    @Override
    public Map delete(int[] ids) {
        return null;
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Product query(int id) {
        return null;
    }

    /**
     * 获取记录数
     * @return
     * @throws Exception
     */
    public Integer getCount(String str,List<Object> list) throws  Exception{
        StringBuffer sql = new StringBuffer(" select count(id) from product where 1=1 ");
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
class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product=new Product();
        product.setId(resultSet.getInt("id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setProductContent(resultSet.getString("product_content"));
        product.setProductCreatetime(resultSet.getString("product_createtime"));
        product.setProductImageurl(resultSet.getString("product_imageurl"));
        product.setProductUrl(resultSet.getString("product_url"));
        product.setProductStatus(Integer.parseInt(resultSet.getString("product_status")));
        product.setProductDetails(resultSet.getString("product_details"));
        return product;
    }



}
