package com.wordbook.api;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//使用 dubbo
@EnableDubbo
// 注册进 nacos
@EnableDiscoveryClient
public class WordbookApplication {
    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(WordbookApplication.class);
        springApplication.run(args);
    }
}
