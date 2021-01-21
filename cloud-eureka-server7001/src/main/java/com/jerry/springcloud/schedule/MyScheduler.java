package com.jerry.springcloud.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableScheduling
//@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
@EnableRetry
@Slf4j
public class MyScheduler {
    AtomicInteger retry = new AtomicInteger();

    //@Scheduled(cron = "0 16 16 * * *")
    //@Scheduled(fixedDelay = 5000)
    /*@SchedulerLock(name = "MyScheduler_lock", lockAtLeastForString = ""PT5M", lockAtMostForString ="PT14M")*/
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 1000))
    public void retryAndRecover() throws Exception {
        retry.getAndIncrement();

        log.info("Scheduling Service Failed " + retry + " Thread" + Thread.currentThread().getId()
                + " Name" + Thread.currentThread().getName());
        throw new Exception("Sheduled Exception!!!!!!!!!!!");
    }

    @Recover
    public void recover(Exception e) {
        log.info(e.getMessage() + "Service recovering");
    }
}