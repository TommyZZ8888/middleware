package com.ys.rabbitmq.annoversion.receive;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "log.debug", autoDelete = "true"),
        exchange = @Exchange(value = "${mq.topic.exchange}", type = ExchangeTypes.TOPIC),
        key = "*.log.debug"
))
public class TopicDebugReceive {

    @RabbitHandler
    public void topicDebugReceive(String msg) {
        System.out.println("topic debug receive " + msg);
    }

}
