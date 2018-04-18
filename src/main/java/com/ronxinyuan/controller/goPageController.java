package com.ronxinyuan.controller;

import com.ronxinyuan.bean.Menu;
import com.ronxinyuan.bean.MenuConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13045 on 2018/2/23.
 */
@Controller
public class goPageController {
    @RequestMapping("/")
    public String toIndex()
    {
        return "index";
    }

    @RequestMapping("/main/{page}")
    public String toPage(@PathVariable("page") String page)
    {
        return "main/"+page;
    }

    @RequestMapping("/getMenu")
    @ResponseBody
    public List<MenuConfig> getMenu(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        MenuConfig menuConfig = new MenuConfig();
        Menu menu = new Menu();
        menu.setText("首页内容");
        List<Map<String,String>> list= new ArrayList<Map<String,String>>();
        Map<String,String> map = new HashMap<String,String>();
        map.put("id","news");
        map.put("text","首页新闻");
        map.put("href","/main/news");
        map.put("closeable","false");
        list.add(map);
        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("id","products");
        map1.put("text","首页产品");
        map1.put("href","/main/products");
        list.add(map1);
        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("id","broadcast");
        map2.put("text","首页广播");
        map2.put("href","/main/broadcast");
        list.add(map2);
        menu.setItems(list);
        List<Menu> menuList = new ArrayList<Menu>();
        menuList.add(menu);
        menuConfig.setId("menu");
        menuConfig.setHomePage("news");
        menuConfig.setMenu(menuList);
        List<MenuConfig> menuConfigList = new ArrayList<MenuConfig>();
        menuConfigList.add(menuConfig);
        return menuConfigList;
    }
}
