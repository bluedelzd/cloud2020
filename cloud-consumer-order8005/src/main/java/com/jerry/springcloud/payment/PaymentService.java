package com.jerry.springcloud.payment;

import common.jerry.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "CLOUD-PAYMENTMAIN-SERVICE")
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@RequestHeader("Auth") String header,  @PathVariable("id") Long id);

    @GetMapping("/payment/error/{id}")
    public String getErrorById(@RequestHeader("Auth") String header,  @PathVariable("id") Long id);
}