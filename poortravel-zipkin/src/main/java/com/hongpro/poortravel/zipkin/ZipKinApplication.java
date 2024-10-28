package com.hongpro.poortravel.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

/**
 * Created by Rainer on 2019/11/27.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
@EnableDiscoveryClient
public class ZipKinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipKinApplication.class, args);
    }
}
