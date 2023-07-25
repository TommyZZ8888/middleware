package com.zz.redis.publishsubscribe;

import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPubSub;

/**
 * @Description JedisSubscribeService
 * @Author 张卫刚
 * @Date Created on 2023/7/25
 */
@Service
public class JedisSubscribeListener extends JedisPubSub {


    @Override
    public void onMessage(String channel, String message) {
        System.out.println("i get the message from channel,saied: " + message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.printf("【" + channel + "订阅频道成功】频道：%s；频道数：%d。%n", channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.printf("【" + channel + "取消订阅】频道：%s；频道数：%d。%n", channel, subscribedChannels);
    }
}
