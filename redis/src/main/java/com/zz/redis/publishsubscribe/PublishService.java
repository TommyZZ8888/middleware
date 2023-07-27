package com.zz.redis.publishsubscribe;

import com.zz.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @Description PublishSubscribeTest
 * @Author 张卫刚
 * @Date Created on 2023/7/25
 */
@Component
public class PublishService {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String redisChannel = "rediscom";


    /**
     * redistemplate
     * @param channel
     * @param message
     */
    public void publish(String channel,Object message){
        redisTemplate.convertAndSend(channel,message);
    }


    public void jedisPublish(String channel,String message) throws InterruptedException {
        Jedis jedis = RedisUtils.getJedis();
        jedis.publish(channel,message);
    }


//    @Bean
//    CommandLineRunner commandLineRunner(){
//        return args -> {
//            Jedis jedis = RedisUtils.getJedis();
//            JedisSubscribeListener jedisSubscribeListener = new JedisSubscribeListener();
//            jedis.subscribe(jedisSubscribeListener,"jedisChannel");
//
//            TimeUnit.SECONDS.sleep(3);
//
//            jedisPublish("jedisChannel","hello redis");
//            TimeUnit.SECONDS.sleep(3);
//
//            jedisSubscribeListener.unsubscribe("jedisChannel");
//
//
//        };
//    }


}
