package com.jerry.springcloud.payment;

import common.jerry.springcloud.entities.CommonResult;
import common.jerry.springcloud.entities.Payment;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class PaymentFallbackFactory implements FallbackFactory<PaymentService> {
    @Override
    public PaymentService create(Throwable error) {
        return new PaymentService() {
            @Override
            public String paymentInfo_OK(Long id) {
                return "----PaymentFallbackService fall back-paymentInfo_OK,...";
            }

            @Override
            public String paymentInfo_TimeOut(Long id) {
                return "----PaymentFallbackService fall back-paymentInfo_TimeOut,...";
            }

            @Override
            public String paymentInfo_Exception4XX(@PathVariable("id") Long id) throws FeignException {
                System.out.println(error);

                if(error instanceof FeignException) {
                    FeignException feignException = (FeignException) error;
                    if(feignException.status() < 500) throw feignException;

                    String message = new String(((FeignException) error).content());
                    return message + " should be here";
                }
                else return "warrped exception";
            }

            @Override
            public String paymentInfo_Exception5XX(@PathVariable("id") Long id) {
                String message = new String(((FeignException)error).content());
                return message + " should be here";
            }

            @Override
            public CommonResult payment_Save(@RequestBody Payment payment){
                return new CommonResult();
            }
        };
    }
}
