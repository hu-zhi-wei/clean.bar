package com.bar.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 服务提供者的中转站 GATEWAY
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ServerApi {

    public static void main(String[] args) {
        SpringApplication.run(ServerApi.class, args);
    }
}
