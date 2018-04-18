package com.ronxinyuan.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13045 on 2018/4/18.
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean filterDemo4Registration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new LoginFilter());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/");
        urlPatterns.add("/index");
        urlPatterns.add("/#menu/*");
        urlPatterns.add("/index.html");
        urlPatterns.add("/main/*");
        //拦截规则
        registration.setUrlPatterns(urlPatterns);
        //过滤器名称
        registration.setName("LoginFilter");
        //是否自动注册 false 取消Filter的自动注册
        //registration.setEnabled(false);
        //过滤器顺序
        registration.setOrder(1);
        return registration;
    }
}
