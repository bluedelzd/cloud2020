package com.jerry.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/discovery")
    public Object discovery(){
        //获取服务列表
        List<String> services = discoveryClient.getServices();
        for(String element : services){
            log.info("****element: "+element);
        }

        //获取具体服务下的实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"/t"+instance.getHost()+"/t"+instance.getPort()+"/t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return "nacos registry, serverPort: "+ serverPort+"\t id"+id;
    }
}