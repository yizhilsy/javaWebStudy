package com.Itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    @Override   //初始化方法，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("init");
    }

    @Override   //拦截到请求后调用，会多次调用
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("DemoFilter放行后");
    }

    @Override   //析构函数
    public void destroy() {
        Filter.super.destroy();
        System.out.println("销毁");
    }
}
