package com.ys.rabbitmq.send;

import com.ys.rabbitmq.config.RabbitMQTopicConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class TopicSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * topic 模式
     */
    public void sendTopic(JSONObject obj) {

        log.info("sendTopic已发送消息");
        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQTopicConfig.TOPIC_EXCHANGE, "rabbitmq.message", obj);
    }
}
