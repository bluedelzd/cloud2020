package com.jerry.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //如果是用IP地址就不能用loadbalance，必须要服务名
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}