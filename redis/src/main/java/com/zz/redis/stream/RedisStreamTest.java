package com.zz.redis.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description RedisStreamTest
 * Redis 本身是有一个 Redis 发布订阅 (pub/sub) 来实现消息队列的功能，支持发布 / 订阅，支持多组生产者、消费者处理消息
 * 但是存在很多问题  1.消费者下线，数据会丢失。 2.不支持数据持久化，Redis 宕机，数据也会丢失
 * Redis Stream 提供了消息的持久化和主备复制功能，可以让任何客户端访问任何时刻的数据，并且能记住每一个客户端的访问位置，还能保证消息不丢失。
 * @Author 张卫刚
 * @Date Created on 2023/7/25
 */

@Component
public class RedisStreamTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String redisKey = "rediscom";

    //windows redis版本低，不支持 需版本5.0以上
    public RecordId xAdd(String key, Map<String, Object> content) {
        return redisTemplate.opsForStream().add(key, content);
    }


//    @Bean
//    CommandLineRunner commandLineRunner() {
//        return args ->
//                xAdd(redisKey, new HashMap<>() {
//                            {
//                                put("name", "stefanie");
//                                put("age", "18");
//                            }
//                        }
//                );
//    }

}
