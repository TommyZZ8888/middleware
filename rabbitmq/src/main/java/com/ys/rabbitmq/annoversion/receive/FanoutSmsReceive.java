package com.ys.rabbitmq.annoversion.receive;


import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "log.sms", autoDelete = "true"),
        exchange = @Exchange(value = "${mq.fanout.exchange}", type = ExchangeTypes.FANOUT)
))
public class FanoutSmsReceive {


    @RabbitHandler
    public void smsReceive(String msg) {
        System.out.println("fanout sms receive " + msg);
    }
}
