package com.jerry.cloud.alibaba;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigMain
{
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigMain.class, args);
    }
}

