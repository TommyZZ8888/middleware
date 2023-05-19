package com.ys.rabbitmq.annoversion.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TopicSend {


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${mq.topic.exchange}")
    private String exchange;

    public void topicSend(String msg){
//        amqpTemplate.convertAndSend(exchange,"user.log.error",msg);
        amqpTemplate.convertAndSend(exchange,"user.log.info",msg);
//        amqpTemplate.convertAndSend(exchange,"user.log.debug",msg);
    }

}
