package com.www.demo;

import com.www.demo.example01.service.consumer.ConsumerService;
import com.www.demo.example01.service.producer.ProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaApplicationTests {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ConsumerService consumerService;

    @Test
    void contextLoads() {

    }

}
