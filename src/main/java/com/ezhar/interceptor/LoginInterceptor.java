package com.ezhar.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//登陆拦截器
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否登陆
        if(request.getSession().getAttribute("user")==null){
            response.sendRedirect("/admin");
            return false;
        }

        return true;
    }
}
