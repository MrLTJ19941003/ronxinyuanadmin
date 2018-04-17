package com.ronxinyuan.bean;

/**
 * Created by 13045 on 2018/4/17.
 */
public class Product {
    private int id;
    private String productName;
    private String productContent;
    private String productCreatetime;
    private String productUrl = "/product/product_show";
    private String productImageurl;
    private String productDetails;
    private int productStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public String getProductCreatetime() {
        return productCreatetime;
    }

    public void setProductCreatetime(String productCreatetime) {
        this.productCreatetime = productCreatetime;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductImageurl() {
        return productImageurl;
    }

    public void setProductImageurl(String productImageurl) {
        this.productImageurl = productImageurl;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productContent='" + productContent + '\'' +
                ", productCreatetime='" + productCreatetime + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", productImageurl='" + productImageurl + '\'' +
                ", productDetails='" + productDetails + '\'' +
                ", productStatus=" + productStatus +
                '}';
    }
}
