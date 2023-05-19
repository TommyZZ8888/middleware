package com.ys.rabbitmq.configversion.send;

import com.ys.rabbitmq.configversion.config.RabbitMQDirectConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DirectSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirect(JSONObject obj) {

        log.info("sendDirectQueue已发送消息");
        // 第一个参数是指要发送到哪个队列里面， 第二个参数是指要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQDirectConfig.DIRECT_QUEUE, obj);
    }
}
