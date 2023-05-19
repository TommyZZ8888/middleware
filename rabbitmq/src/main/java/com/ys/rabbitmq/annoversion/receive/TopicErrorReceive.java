package com.ys.rabbitmq.annoversion.receive;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "log.error", autoDelete = "true"),
        exchange = @Exchange(value = "${mq.topic.exchange}", type = ExchangeTypes.TOPIC),
        key = "*.log.error"
))
public class TopicErrorReceive {


    @RabbitHandler
    public void topicErrorReceive(String msg) {
        System.out.println("topic error receive " + msg);
    }
}
