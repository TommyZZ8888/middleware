package com.zz.redis.fivetype;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description StringRedisTest
 * @Author 张卫刚
 * @Date Created on 2023/7/24
 */
@Component
public class StringRedisTest {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public void set(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }


//    @Bean
//    CommandLineRunner commandLineRunner() {
//        return args -> set("myStringKey","myStringValue");
//    }

}
