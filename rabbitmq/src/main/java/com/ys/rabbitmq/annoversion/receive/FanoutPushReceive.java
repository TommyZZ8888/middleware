package com.ys.rabbitmq.annoversion.receive;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "log.push", autoDelete = "true"),
        exchange = @Exchange(value = "${mq.fanout.exchange}", type = ExchangeTypes.FANOUT)

))
public class FanoutPushReceive {

    @RabbitHandler
    public void pushReceive(String msg) {
        System.out.println("fanout push receive " + msg);
    }
}
