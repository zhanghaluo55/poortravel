package com.hongpro.poortravel.service.attraction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.hongpro.poortravel")
@EnableEurekaClient
@EnableSwagger2
@MapperScan(basePackages = {"com.hongpro.poortravel.common.mapper", "com.hongpro.poortravel.service.attraction.mapper"})
public class ServiceAttractionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAttractionApplication.class, args);
    }

}
