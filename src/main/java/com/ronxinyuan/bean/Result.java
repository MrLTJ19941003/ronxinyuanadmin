package com.ronxinyuan.bean;

/**
 * Created by 13045 on 2018/2/23.
 */
public class Result {
    private int status;
    private String date;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Result(int status, String date) {
        this.status = status;
        this.date = date;
    }
}
