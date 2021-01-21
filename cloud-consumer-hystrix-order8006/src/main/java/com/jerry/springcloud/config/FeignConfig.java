package com.jerry.springcloud.config;

import com.jerry.springcloud.exception.ErrorCallException;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Logger;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;//日志级别
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new SpecialErrorDecoder();
    }

    public class SpecialErrorDecoder extends ErrorDecoder.Default  {
        @Override
        public Exception decode(String methodKey, Response response) {
            Exception exception = null;

            // 这里只封装4开头的请求异常
            if (400 <= response.status() && response.status() < 500){
                //exception = new HystrixBadRequestException("request exception wrapper " + response.reason());
                exception = null;
            }else{
                exception = new ErrorCallException(response.reason() + " status" + response.status());
            }

            return exception;
        }
    }
}