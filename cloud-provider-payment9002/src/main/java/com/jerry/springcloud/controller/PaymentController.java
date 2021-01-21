package com.jerry.springcloud.controller;

import com.jerry.springcloud.service.PaymentService;
import common.jerry.springcloud.entities.CommonResult;
import common.jerry.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/zk")
    public String paymentzk(){
        return "springcloud with zookeeper"+serverPort+"\t"+ UUID.randomUUID().toString();
    }

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


    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果：{result}");
        if (result > 0) {
            return new CommonResult(200, "插入成功", result);
        } else {
            return new CommonResult(444, "插入失败");
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****插入结果：{payment}");
        if (payment != null) {
            return new CommonResult(200, "查询成功", payment);
        } else {
            return new CommonResult(444, "无记录");
        }
    }
}