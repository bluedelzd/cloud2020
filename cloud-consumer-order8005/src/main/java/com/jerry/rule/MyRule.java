package com.jerry.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRule {
    @Bean
    public IRule myRule(){
        RoundRobinRule r;
        return new RandomRule();//随机负载均衡算法
    }
}