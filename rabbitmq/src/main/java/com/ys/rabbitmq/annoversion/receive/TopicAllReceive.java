package com.ys.rabbitmq.annoversion.receive;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "log.all", autoDelete = "true"),
        exchange = @Exchange(value = "${mq.topic.exchange}", type = ExchangeTypes.TOPIC),
        key = "*.log.#"
))
public class TopicAllReceive {


    @RabbitHandler
    public void topicAllReceive(String msg) {
        System.out.println("topic all receive " + msg);
    }
}
