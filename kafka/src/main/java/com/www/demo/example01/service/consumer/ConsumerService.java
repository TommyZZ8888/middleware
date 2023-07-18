//package com.www.demo.example01.service.consumer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
///**
// * @Describtion: ConsumerService
// * @Author: 张卫刚
// * @Date: 2023/7/16 15:33
// */
//@Component
//@Slf4j
//public class ConsumerService {
//
//    @KafkaListener(topics = "kafka_topic", groupId = "my-group-id")
//    public void receive(String message) {
//        System.out.println("receive message: " + message);
//    }
//
//
//    @KafkaListener(topics = "kafka_topic")
//    public void consumer(ConsumerRecord<?, ?> record) {
//        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//        if (kafkaMessage.isPresent()) {
//            Object message = kafkaMessage.get();
//            log.info("----------------- record =" + record);
//            log.info("------------------ message =" + message);
//        }
//    }
//}
