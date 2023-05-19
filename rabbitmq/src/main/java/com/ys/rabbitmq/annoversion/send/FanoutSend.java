package com.ys.rabbitmq.annoversion.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FanoutSend {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${mq.fanout.exchange}")
    private String exchange;

    public void fanoutSend(String msg) {
        amqpTemplate.convertAndSend(exchange, "", msg);
    }
}
