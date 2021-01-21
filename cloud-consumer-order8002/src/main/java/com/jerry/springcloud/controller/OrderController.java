package com.jerry.springcloud.controller;

import com.jerry.springcloud.config.LoadBalancer;
import common.jerry.springcloud.entities.CommonResult;
import common.jerry.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    private static final String PAYMENT_URL = "http://cloud-PaymentMain-service";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;//自定义的
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENTMAIN-SERVICE");
        if(serviceInstances == null || serviceInstances.size() <= 0){
            return null;
        }
        ServiceInstance instance = loadBalancer.instances(serviceInstances);
        URI url = instance.getUri();
        //return restTemplate.getForObject(url+"/payment/lb",String.class); //自己写的loadbalance
        return restTemplate.getForObject(PAYMENT_URL+"/payment/lb",String.class); //默认服务名
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/payment/get/entity/{id}")
    public CommonResult<Payment> getPaymentEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> e = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        if(e.getStatusCode().is2xxSuccessful()) return e.getBody();
        else return new CommonResult<>(444, "fail");
    }
}