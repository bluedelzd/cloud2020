package com.jerry.springcloud.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.jerry.springcloud.dao"})
public class MyBatisConfig {
}