package com.ys.rabbitmq.annoversion.receive;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${mq.direct.queue}", autoDelete = "true"),
        exchange = @Exchange(value = "${mq.direct.exchange}", type = ExchangeTypes.DIRECT),
        key = "${mq.direct.routingKey}"))
public class DirectReceive {


    @RabbitHandler
    public void directReceive(String msg) {
        System.out.println("direct receive "+msg);
    }

}
