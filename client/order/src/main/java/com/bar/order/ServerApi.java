package com.bar.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages= {
        "com.bar.common.aop",
        "com.bar.common.custom",
        "com.bar.common.service",
        "com.bar.order.service",
        "com.bar.order.web"
})
@MapperScan({"com.bar.common.mapper", "com.bar.order.mapper"})
@PropertySource(value = "classpath:config.properties")
@ServletComponentScan
@EnableEurekaClient
public class ServerApi {

    public static void main(String[] args) {
        SpringApplication.run(ServerApi.class, args);
    }
}
