package com.www.demo.service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @Describtion: ConsumerService
 * @Author: 张卫刚
 * @Date: 2023/7/16 15:33
 */
@Service
public class ConsumerService {

    @KafkaListener(topics = "kafka_topic", groupId = "my-group-id")
    public void receive(String message) {
        System.out.println("receive message: " + message);
    }

}
