package com.jerry.springcloud;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
//@EnableEurekaClient
//@EnableDiscoveryClient
public class PaymentMain {
    public static void main(String[] args) throws Exception {

        SpringApplication.run(PaymentMain.class, args);
        ObjectMapper OBJECT_MAPPER = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        map.put("_doc", map1);
        map1.put("properties", map2);
        map2.put("message", map3);
        map3.put("type", "keyword");

        String s = OBJECT_MAPPER.writeValueAsString(map);
        System.out.println(s);
    }


}
