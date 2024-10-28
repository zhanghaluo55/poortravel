package com.hongpro.poortravel.service.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.hongpro.poortravel")
public class ServiceUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUploadApplication.class, args);
    }
}
