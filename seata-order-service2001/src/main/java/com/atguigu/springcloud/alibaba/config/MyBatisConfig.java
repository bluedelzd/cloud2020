package com.atguigu.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.atguigu.springcloud.alibaba.dao"})
public class MyBatisConfig {
    private void getTest() {
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RBucket<String> bucket = redisson.getBucket("anyObject");
        bucket.set("");
        bucket.compareAndSet("2", "1");

        RMap<String, String> map = redisson.getMap("");
        map.get("");
        RLock rlock = redisson.getLock("");
        rlock.lock();
        rlock.unlock();

    }
}

