package com.ys.rabbitmq.configversion.send;

import com.ys.rabbitmq.configversion.config.RabbitMQFanoutConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class FanoutSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendFanout(JSONObject obj) {

        log.info("sendFanout已发送消息");
        // 这里的第2个参数为空。
        // 因为fanout 交换器不处理路由键，只是简单的将队列绑定到交换器上，
        // 每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上
        this.amqpTemplate.convertAndSend(RabbitMQFanoutConfig.FANOUT_EXCHANGE, "", obj);
    }
}
