package com.ys.rabbitmq.configversion.receive;

import com.ys.rabbitmq.configversion.config.RabbitMQDirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DirectReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQDirectConfig.DIRECT_QUEUE)
    public void receiverDirect(JSONObject obj) {
        log.info("receiverDirectQueue收到消息" + obj.toString());
    }
}
