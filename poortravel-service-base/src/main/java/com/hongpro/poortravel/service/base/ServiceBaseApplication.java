package com.hongpro.poortravel.service.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.hongpro.poortravel")
@EnableEurekaClient
@EnableSwagger2
@MapperScan(basePackages = {"com.hongpro.poortravel.common.mapper", "com.hongpro.poortravel.service.base.mapper"})
public class ServiceBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceBaseApplication.class, args);
    }
}
