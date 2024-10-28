package com.hongpro.poortravel.common.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConstantsInterceptor implements HandlerInterceptor {
    private static final String HOST_CDN = "";
    private static final String TEMPLATE_ADMIN_LTE = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//        if (modelAndView!=null){
//            modelAndView.addObject("adminlte"+HOST_CDN+"/"+TEMPLATE_ADMIN_LTE);
//        }
    }
}
