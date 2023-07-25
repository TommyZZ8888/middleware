package com.zz.redis.publishsubscribe;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @Description SubscribeService
 * @Author 张卫刚
 * @Date Created on 2023/7/25
 */
@Component
public class SubscribeService implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(message);
        System.out.println(pattern);
    }

}
