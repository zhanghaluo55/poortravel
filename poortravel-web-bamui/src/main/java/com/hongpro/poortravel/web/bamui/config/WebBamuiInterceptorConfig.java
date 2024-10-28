package com.hongpro.poortravel.web.bamui.config;

import com.hongpro.poortravel.web.bamui.interceptor.WebBamuiInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebBamuiInterceptorConfig implements WebMvcConfigurer {
    @Bean
    WebBamuiInterceptor webBamuiInterceptor() {
        return new WebBamuiInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webBamuiInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static");
    }
}
