package com.zz.redis.config;

import com.zz.redis.publishsubscribe.SubscribeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description RedisConfig
 * @Author 张卫刚
 * @Date Created on 2023/7/24
 */

@Configuration
public class RedisConfig {

    private static final String redisChannel = "rediscom";

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)     {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        return redisTemplate;
    }


//    @Bean
//    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory factory,
//                                                           MessageListenerAdapter messageListenerAdapter){
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(factory);
//        container.addMessageListener(messageListenerAdapter, new PatternTopic(redisChannel));
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter messageListenerAdapter(SubscribeService redisReceiver){
//        return new MessageListenerAdapter(redisReceiver,
//                "onMessage");
//    }


}
