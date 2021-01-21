package com.jerry.springcloud;

import com.jerry.springcloud.schedule.MyScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaServer
public class EurekaMain {

    public static void main(String[] args) throws Exception{
        ApplicationContext applicationContext = SpringApplication.run(EurekaMain.class,args);
        MyScheduler myScheduler = applicationContext.getBean(MyScheduler.class);
        myScheduler.retryAndRecover();

        System.out.println("=================+Here");
    }
}