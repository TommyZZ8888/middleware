package com.www.demo.example02.controller;

import com.www.demo.example02.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description KafkaController
 * @Author 张卫刚
 * @Date Created on 2023/7/18
 */
@RestController
public class KafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/send")
    public void sendMsg(){
        kafkaProducer.send("------------测试消息-----------");
    }
}
