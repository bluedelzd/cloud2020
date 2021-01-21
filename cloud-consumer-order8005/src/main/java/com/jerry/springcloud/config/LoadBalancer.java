package com.jerry.springcloud.config;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    //从服务列表中选取一个服务
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}