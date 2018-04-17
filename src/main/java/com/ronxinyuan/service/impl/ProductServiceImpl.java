package com.ronxinyuan.service.impl;

import com.ronxinyuan.bean.AdminResult;
import com.ronxinyuan.bean.Content;
import com.ronxinyuan.bean.Page;
import com.ronxinyuan.bean.Product;
import com.ronxinyuan.common.StringUtils;
import com.ronxinyuan.dao.ProductDao;
import com.ronxinyuan.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liutj on 2018/2/23.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductDao productDao;

    /**
     * 新增
     * @param request
     * @return
     */
    @Override
    public Map save(HttpServletRequest request) {
        Map map =new HashMap();
        Product product = new Product();
        try {
            String id=request.getParameter("id");
            String productName = request.getParameter("productName");
            String  productContent= request.getParameter("productContent");
            String  productTime= request.getParameter("productTime");
            String  imageUrl= request.getParameter("imageUrl");
            String  productDetails= request.getParameter("productDetails");
            int productStatus = StringUtils.MyparseInt(request.getParameter("productStatus"));
            if(StringUtils.isEmpty(productName) && StringUtils.isEmpty(productContent) && StringUtils.isEmpty(productTime) && StringUtils.isEmpty(imageUrl)&& StringUtils.isEmpty(productDetails)){
                throw new Exception("参数不正确");
            }
            product.setProductName(productName);
            product.setProductContent(productContent);
            product.setProductDetails(productDetails);
            product.setProductCreatetime(productTime);
            product.setProductStatus(productStatus);
            product.setProductImageurl(imageUrl);
            logger.debug("add参数："+product);
            if(StringUtils.isEmpty(id)){//判断是新增还是修改
                return productDao.save(product);
            }else{
                product.setId(StringUtils.MyparseInt(id));
                return productDao.edit(product);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("add错误："+e);
            map.put("status","false");
            map.put("error",e);
            return map;
        }
    }

    /**
     * 查询全部
     * @param request
     * @return
     */
    @Override
    public AdminResult getDate(HttpServletRequest request) {
        Page page=new Page();
        Product product = new Product();
        int limit,start,pageIndex,id;
        String productName,createTime,productDetails;
        try {
            limit = StringUtils.MyparseInt(request.getParameter("limit"));
            start = StringUtils.MyparseInt(request.getParameter("start"));
            pageIndex = StringUtils.MyparseInt(request.getParameter("pageIndex"));
            String ids = request.getParameter("id");
            if(ids!=null && ids!=""){
                id = StringUtils.MyparseInt(ids);
                product.setId(id);
            }else{
                product.setId(-1);
            }
            productName = request.getParameter("productName");
            createTime = request.getParameter("createTime");
            productDetails = request.getParameter("productDetails");
            page.setStart(start);
            page.setLimit(limit);
            page.setPageIndex(pageIndex);

            product.setProductName(productName);
            product.setProductCreatetime(createTime);
            product.setProductDetails(productDetails);
        }catch (Exception e){
            return new AdminResult(true,"参数有误"+e.toString());
        }
        return productDao.getDate(page,product);
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
     * 删除
     * @param ids
     * @return
     */
    @Override
    public Map delete(int[] ids) {
        return null;
    }

}
