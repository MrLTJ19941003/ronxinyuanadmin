package com.ronxinyuan.bean;

/**
 * Created by 13045 on 2018/3/26.
 */
public class Page {
    private int start;
    private int limit;
    private int pageIndex;


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", limit=" + limit +
                ", pageIndex=" + pageIndex +
                '}';
    }
}
