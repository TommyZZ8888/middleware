package com.www.demo;

import com.www.demo.service.producer.ProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaApplicationTests {

    @Autowired
    private ProducerService producerService;

    @Test
    void contextLoads() {
    producerService.send("kafka learn first");

    }

}
