package com.jerry.springcloud.controller;

import com.google.common.collect.Maps;
import com.jerry.springcloud.service.PaymentService;
import common.jerry.springcloud.entities.CommonResult;
import common.jerry.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("****result："+result);
        return result;
    }

    @GetMapping("/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("****result："+result);
        return result;
    }

    //服务熔断
    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****result："+result);
        return result;
    }

    //服务熔断
    @GetMapping("/circuit1/{id}")
    public String paymentCircuitBreaker1(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker1(id);
        log.info("****result："+result);
        return result;
    }

    //服务熔断
    @GetMapping("/circuit2/{id}")
    public String paymentCircuitBreaker2(@PathVariable("id") Integer id){
        throw new IllegalArgumentException("Parameter Id is not vaid, id:" + id);
    }


    //服务熔断
    @GetMapping("/circuit3/{id}")
    public String paymentCircuitBreaker3(@PathVariable("id") Integer id){
        throw new ArithmeticException("Arithmetic Id is not vaid, id:" + id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    ResponseEntity handleNotFoundException(IllegalArgumentException ex) {
        return new ResponseEntity<>(Maps.immutableEntry("error", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    ResponseEntity handleNotFoundException(ArithmeticException ex) {
        return new ResponseEntity<>(Maps.immutableEntry("error", ex.getMessage()), HttpStatus.NOT_IMPLEMENTED);
    }
}