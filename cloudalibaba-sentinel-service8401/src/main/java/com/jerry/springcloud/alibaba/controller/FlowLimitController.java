package com.jerry.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class FlowLimitController
{
    @GetMapping("/testA")
    public String testA() throws InterruptedException{
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + " Test B");
        return "------testB";
    }


    @GetMapping("/testD")
    public String testD()
    {
        log.info("testD 测试RT");
        int age = 10/0;

        return "------testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_hostKey")
    public String testHotKey(@RequestParam(value = "p1", required = false)String p1,
                             @RequestParam(value = "p2", required = false)String p2){
        return "Test Hot key";
    }

    public String deal_hostKey(String p1, String p2, BlockException blockException){
        return "Deal Hot key";
    }
}

