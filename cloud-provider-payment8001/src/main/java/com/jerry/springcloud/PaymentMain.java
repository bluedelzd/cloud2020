package com.jerry.springcloud;

import com.jerry.springcloud.controller.PaymentController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
//@EnableCaching
public class PaymentMain {
    public static void main(String[] args) {
        System.out.println(PaymentMain.class.getClassLoader());
        System.out.println(PaymentController.class.getClassLoader());
        SpringApplication.run(PaymentMain.class, args);
    }
}
