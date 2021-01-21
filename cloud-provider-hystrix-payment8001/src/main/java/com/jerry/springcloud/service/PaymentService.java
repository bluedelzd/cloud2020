package com.jerry.springcloud.service;


import cn.hutool.core.util.IdUtil;
import com.jerry.springcloud.dao.PaymentDao;
import com.jerry.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import common.jerry.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "defaultFallback")
public class PaymentService {

    /*
    正确的方法
     */
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id："+id+"\t"+"???";
    }

    /*
    会超时报错的方法
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 5;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOut,id："+id+"\t"+"！！！";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否启用断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0)
            throw new RuntimeException("****id 不能为负数");
        String serialNumber = IdUtil.simpleUUID();//hutool工具
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker1(@PathVariable("id") Integer id){
        if(id<0)
            throw new RuntimeException("****id 不能为负数");
        String serialNumber = IdUtil.simpleUUID();//hutool工具
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id："+id+"，不能为负数，请稍后再试，。。。";
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+ "paymentInfo_TimeoutHandler,id："+id+"\t"+"！！！";
    }
}