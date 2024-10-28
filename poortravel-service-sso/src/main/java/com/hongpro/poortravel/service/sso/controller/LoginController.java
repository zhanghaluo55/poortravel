package com.hongpro.poortravel.service.sso.controller;

import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.common.utils.CookieUtils;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.sso.service.LoginService;
import com.hongpro.poortravel.service.sso.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    /**
     * 跳转登录页
     *
     * @return
     */

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String url, HttpServletRequest request, Model model) {
        String token = CookieUtils.getCookieValue(request, "token");
        //token不为空可能已登录
        if (StringUtils.isNotBlank(token)) {
            String json = redisService.get(token);
            if (StringUtils.isNotBlank(json)) {
                try {
                    User user = MapperUtils.json2pojo(json, User.class);

                    //已登录
                    if (user != null) {
                        if (StringUtils.isNotBlank(url)) {
                            return "redirect:" + url;
                        }
                    }

                    //将登陆信息传到登录页
                    model.addAttribute("user", user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (StringUtils.isNotBlank(url)) {
            model.addAttribute("url", url);
        }
        return "login";
    }

    /**
     * 登录业务
     *
     * @param username
     * @param password
     * @return
     */

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password,
            @RequestParam(required = false) String url,
            HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        User user = loginService.login(username, password);
        redirectAttributes.addFlashAttribute("url",url);
        //登录失败
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "用户名或者密码错误，请重新输入");
        }

        //登录成功
        else {
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, username, 60 * 60 * 24);
            if (StringUtils.isNotBlank(result) && "ok".equals(result)) {
                CookieUtils.setCookie(request, response, "token", token, 60 * 60 * 24);

                request.getSession().setAttribute("user",user);
                if (StringUtils.isNotBlank(url)) {
                    return "redirect:" + url;
                }
            }
            //熔断处理
            else {
                redirectAttributes.addFlashAttribute("message", "服务器异常，请稍后再试");
            }
        }

        return "redirect:/login";
    }

    /* *//**
     * 跳转首页
     * @return
     *//*
    @RequestMapping(value = {"index","index.html"},method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response){
        return "index";
    }*/


    /**
     * 注销
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "loginout", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            CookieUtils.deleteCookie(request, response, "token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login("", request, model);
    }
}
