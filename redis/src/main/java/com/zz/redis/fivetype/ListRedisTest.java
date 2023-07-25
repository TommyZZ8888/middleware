package com.zz.redis.fivetype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description ListRedisTest
 * @Author 张卫刚
 * @Date Created on 2023/7/25
 */
@Component
public class ListRedisTest {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void lPush(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    public void lLeftPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public void lPushAll(String key, Object... value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }


    public List<Object> lGet(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


//    @Bean
//    CommandLineRunner commandLineRunner() {
//        return args -> {
//            lPushAll("rediscom", 2, 3);
//
//            lLeftPush("rediscom", 1);
//
//            lPush("rediscom", 4);
//
//            Long len = lLen("rediscom");
//
//            System.out.println(lGet("rediscom", 0, len.intValue()));
//        };
//    }


}
