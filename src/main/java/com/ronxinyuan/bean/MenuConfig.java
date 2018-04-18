package com.ronxinyuan.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by 13045 on 2018/4/18.
 */
public class MenuConfig {
    private String id;
    private String collapsed;
    private String homePage;
    private List<Menu> menu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollapsed() {
        return collapsed;
    }

    public void setCollapsed(String collapsed) {
        this.collapsed = collapsed;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }
}


