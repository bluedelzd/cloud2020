package com.jerry.springcloud.payment;

import common.jerry.springcloud.entities.CommonResult;
import common.jerry.springcloud.entities.Payment;
import org.redisson.Redisson;
import org.redisson.RedissonMap;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public String paymentInfo_OK(Long id) {
        return "----PaymentFallbackService fall back-paymentInfo_OK,...";
    }

    @Override
    public String paymentInfo_TimeOut(Long id) {
        return "----PaymentFallbackService fall back-paymentInfo_TimeOut,...";
    }

    @Override
    public String paymentInfo_Exception4XX(@PathVariable("id") Long id){
      //  RBucket<String>  s = redissonClient.getBucket("s");
        //RMap<String, String> map = redissonClient.getMap("Test");
        return "should not be here";
    }

    @Override
    public String paymentInfo_Exception5XX(@PathVariable("id") Long id){
        return "should be here";
    }

    @Override
    public CommonResult payment_Save(@RequestBody Payment payment){
        return new CommonResult();
    }
}