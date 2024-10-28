package com.hongpro.poortravel.web.bamui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.hongpro.poortravel")
@EnableDiscoveryClient
@EnableFeignClients
public class WebBamuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBamuiApplication.class, args);
    }

}
