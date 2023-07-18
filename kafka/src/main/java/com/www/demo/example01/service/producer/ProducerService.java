//package com.www.demo.example01.service.producer;
//
//import com.www.demo.KafkaApplication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
///**
// * @Describtion: ProducerService
// * @Author: 张卫刚
// * @Date: 2023/7/16 15:26
// */
//@Service
//public class ProducerService {
//
//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;
//
////    @Value("${kafka.topic}")
////    private String kafkaTopic;
//
//
//    public void send(String message){
//        kafkaTemplate.send("kafka_topic",message);
//    }
//
//}
