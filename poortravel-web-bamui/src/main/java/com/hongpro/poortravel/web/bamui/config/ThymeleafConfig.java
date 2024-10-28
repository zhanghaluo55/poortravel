package com.hongpro.poortravel.web.bamui.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Configuration
public class ThymeleafConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void addFormatters(final FormatterRegistry registry){
        super.addFormatters(registry);
        registry.addFormatter(dateFormatter());
    }

    @Bean
    public DateFormatter dateFormatter(){
        return new MyDateFormatter();
    }

    class MyDateFormatter extends DateFormatter {
        @Override
        public String print(Date date, Locale locale) {
            //return super.print(date, locale);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        }
    }
}
