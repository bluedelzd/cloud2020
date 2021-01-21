package com.jerry.springcloud.service.imp;


import com.jerry.springcloud.dao.PaymentDao;
import com.jerry.springcloud.service.PaymentService;
import common.jerry.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
@Slf4j
public class PaymentServiceImp implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Autowired
    ApplicationContext context;

    @Bean
    Test getTest(){
        return new Test();
    }

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Payment getPaymentById(Long id) {
        Mono<String> s = new Mono<String>() {
            @Override
            public void subscribe(CoreSubscriber<? super String> coreSubscriber) {

            }
        };
        s.doOnSuccess(str -> str.length());
        Test t = getTest();
        log.info(t.uuid.toString() + "Get From Method");

        Test bT = context.getBean(Test.class);
        log.info(bT.uuid.toString() + "Get From context Bean");

        return paymentDao.getPaymentById(id);
    }
}
