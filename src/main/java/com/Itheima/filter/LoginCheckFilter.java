package com.Itheima.filter;

import com.Itheima.pojo.Result;
import com.Itheima.utils.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取请求url
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //1.获取请求url
        String url = req.getRequestURI().toString();
        log.info("请求url:{}",url);

        //2.判断请求是否是登录请求
        if(url.contains("login")){
            log.info("登录操作，无条件放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //3.获取请求头中的令牌(token)
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在，如果不存在，返回错误结果
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //因不是Controller类，因此需要手动转换对象->json，使用阿里爸爸fastjson
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //6.放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
