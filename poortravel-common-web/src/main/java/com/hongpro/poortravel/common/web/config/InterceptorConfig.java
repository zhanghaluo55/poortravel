package com.hongpro.poortravel.common.web.config;

import com.hongpro.poortravel.common.web.interceptor.ConstantsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ConstantsInterceptor()).addPathPatterns("/**");
    }
}
