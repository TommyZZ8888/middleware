package com.ys.rabbitmq.annoversion.send;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DirectSend {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${mq.direct.exchange}")
    private String directExchange;

    @Value("${mq.direct.routingKey}")
    private String routingKey;

    public void send(String msg) {
        amqpTemplate.convertAndSend(directExchange, routingKey, msg);
    }

}
