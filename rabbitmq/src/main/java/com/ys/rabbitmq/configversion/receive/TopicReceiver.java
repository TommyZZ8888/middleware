package com.ys.rabbitmq.configversion.receive;

import com.ys.rabbitmq.configversion.config.RabbitMQTopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class TopicReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQTopicConfig.TOPIC_QUEUE)
    public void receiveTopic(JSONObject obj) {
        log.info("receiveTopic收到消息:" + obj.toString());
    }
}
