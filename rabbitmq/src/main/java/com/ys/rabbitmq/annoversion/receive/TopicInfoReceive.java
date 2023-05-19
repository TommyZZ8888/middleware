package com.ys.rabbitmq.annoversion.receive;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "log.info", autoDelete = "true"),
        exchange = @Exchange(value = "${mq.topic.exchange}", type = ExchangeTypes.TOPIC),
        key = "user.log.info"
))

public class TopicInfoReceive {


        @RabbitHandler
        public void topicInfoReceive(String msg) {
            System.out.println("topic info receive " + msg);

    }
}
