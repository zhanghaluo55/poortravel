package com.hongpro.poortravel.web.bamui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Rainer on 2019/5/11.
 */
//@EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("index_v3").setViewName("index_v3");

    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    /*   @Bean
    public WebMvcConfigurationSupport webMvcConfigurationSupport(){
        WebMvcConfigurationSupport support=new WebMvcConfigurationSupport(){
            public void addViewControllers(ViewControllerRegistry registry){
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("main").setViewName("index");
                registry.addViewController("main.html").setViewName("index");
            }
        };
        return support;
    }*/
}
