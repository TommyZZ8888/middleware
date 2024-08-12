package com.www.demo;


import com.www.demo.example02.service.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaApplicationTests {

    @Autowired
    private KafkaProducer producerService;




    @Test
    void contextLoads() {
producerService.send("test");
    }

}
