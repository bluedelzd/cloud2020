package com.jerry.springcloud.payment;

import common.jerry.springcloud.entities.CommonResult;
import common.jerry.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "CLOUD-PAYMENTMAIN-SERVICE", fallbackFactory = PaymentFallbackFactory.class)
public interface PaymentService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id);

    @GetMapping("/payment/circuit2/{id}")
    public String paymentInfo_Exception4XX(@PathVariable("id") Long id);

    @GetMapping("/payment/circuit3/{id}")
    public String paymentInfo_Exception5XX(@PathVariable("id") Long id);

    @PostMapping("/payment/create")
    public CommonResult payment_Save(@RequestBody Payment payment);
}