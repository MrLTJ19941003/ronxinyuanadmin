package com.ronxinyuan.filter;

import com.ronxinyuan.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liutj on 2018/4/18.
 */
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //System.out.println("==>DemoFilter启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       // System.out.println("过滤所有请求");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getContextPath();
        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUsername() == null || "".equals(user.getUsername())) {
            /*resp.setHeader("Cache-Control", "no-store");
            resp.setDateHeader("Expires", 0);
            resp.setHeader("Prama", "no-cache");*/
            //此处设置了访问静态资源类
            PrintWriter out = resp.getWriter();
            out.write("<script>window.parent.location.href='/login'</script>");
            //resp.sendRedirect("<script>window</script>");
        } else {
            // Filter 只是链式处理，请求依然转发到目的地址。
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
