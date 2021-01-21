package com.jerry.springcloud.controller;

import com.jerry.springcloud.payment.PaymentService;
import common.jerry.springcloud.entities.CommonResult;
import common.jerry.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.function.Consumer;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderFeginController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPyamentById(@PathVariable("id")Long id){
        return paymentService.getPaymentById("testHeader", id);
    }

    @GetMapping("/payment/errir/{id}")
    public String getError(@PathVariable("id")Long id){
        return paymentService.getErrorById("testHeader", id);
    }
}