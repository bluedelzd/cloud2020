package com.jerry.springcloud.controller;

import com.jerry.springcloud.payment.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import common.jerry.springcloud.entities.CommonResult;
import common.jerry.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderFeginController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/ok/{id}")
    public String getPyamentByIdOK(@PathVariable("id")Long id){
        return paymentService.paymentInfo_OK(id);
    }


    @GetMapping("/payment/timeout/{id}")
    public String getPyamentByIdTimeout(@PathVariable("id")Long id){
        return paymentService.paymentInfo_TimeOut(id);
    }

    @GetMapping("/payment/c1/{id}")
    public String getPyamentById4xx(@PathVariable("id")Long id){
        return paymentService.paymentInfo_Exception4XX(id);
    }

    @GetMapping("/payment/c2/{id}")
    public String getPyamentById5xx(@PathVariable("id")Long id){
        return paymentService.paymentInfo_Exception5XX(id);
    }

    @GetMapping("/payment/save")
    public String save(){
        Payment payment = new Payment();
        payment.setSerial(UUID.randomUUID().toString());
        CommonResult result = paymentService.payment_Save(payment);
        return result.getMessage();
    }

    public String paymentInfo_TimeOutHandler(Long id){
        return "本80系统繁忙，请稍后再试,id："+id+"\t"+"。。。";
    }

    //全局降级方法
    public String paymentInfo_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，。。。";
    }
}