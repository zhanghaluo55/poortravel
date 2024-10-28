package com.hongpro.poortravel.web.bamui.interceptor;

import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.common.utils.CookieUtils;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.utils.HttpServletUtils;
import com.hongpro.poortravel.web.bamui.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class WebBamuiInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Value("${hosts.sso}")
    private String hosts_sso;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token=CookieUtils.getCookieValue(request,"token");
        System.out.println("host:"+hosts_sso);
        //token为空一定未登录
        if(StringUtils.isBlank(token)){
            try {
                response.sendRedirect(String.format("%s/login?url=%s",hosts_sso,HttpServletUtils.getFullPath(request)));
                //response.sendRedirect(String.format("http://localhost:8503/login?url=%s",HttpServletUtils.getFullPath(request)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        //已登录
        if(user!=null){
            if(modelAndView!=null){
                modelAndView.addObject("user",user);
            }
        }
        //未登录
        else{
            String token=CookieUtils.getCookieValue(request,"token");
            if(StringUtils.isNotBlank(token)){
                String username=redisService.get(token);
                if(StringUtils.isNotBlank(username)){
                    String json=redisService.get(username);
                    if(StringUtils.isNotBlank(json)){
                        try {
                            //已登录创建局部会话
                            user=MapperUtils.json2pojo(json,User.class);
                            if(modelAndView!=null){
                                modelAndView.addObject("user",user);
                            }
                            request.getSession().setAttribute("user",user);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


        //二次确认是否有用户信息
        if (user==null){
            try {
                response.sendRedirect(String.format("%s/login?url=%s",hosts_sso,HttpServletUtils.getFullPath(request)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
