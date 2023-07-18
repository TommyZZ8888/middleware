//package com.www.demo.example01.config;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//
///**
// * @Description KafkaTopicConfig
// * @Author 张卫刚
// * @Date Created on 2023/7/18
// */
//@Configuration
//public class KafkaTopicConfig {
//
//    /**
//     * 初始化topic
//     * 不然 在发送一个不存在的topic时，就会自动创建 只不过分区和副本都默认1
//     * @return
//     */
//    @Bean
//    public NewTopic getKafkaTopic() {
//        return TopicBuilder.name("kafka_topic")
//                .partitions(10)
//                .replicas(1)
//                .build();
//    }
//
//}
