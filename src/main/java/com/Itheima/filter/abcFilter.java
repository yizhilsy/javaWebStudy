package com.Itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class abcFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("abcFilter放行前");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("abcFilter放行后");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
