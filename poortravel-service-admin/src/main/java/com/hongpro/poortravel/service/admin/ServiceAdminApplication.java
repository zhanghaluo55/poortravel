package com.hongpro.poortravel.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by Rainer on 2020/1/9.
 */
@SpringBootApplication(scanBasePackages = "com.hongpro.poortravel")
@EnableEurekaClient
@MapperScan(basePackages = {"com.hongpro.poortravel.service.admin.mapper", "com.hongpro.poortravel.common.mapper"})
public class ServiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class, args);
    }
}
