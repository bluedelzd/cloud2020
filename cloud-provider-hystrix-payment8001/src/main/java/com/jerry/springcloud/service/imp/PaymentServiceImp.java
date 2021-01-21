package com.jerry.springcloud.service.imp;


import com.jerry.springcloud.dao.PaymentDao;
import com.jerry.springcloud.service.PaymentService;
import common.jerry.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImp extends PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
