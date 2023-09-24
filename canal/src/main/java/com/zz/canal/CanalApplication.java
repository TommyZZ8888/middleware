package com.zz.canal;

import com.zz.canal.client.annotation.EnableCanalClient;
import com.zz.canal.client.client.CanalClient;
import com.zz.canal.client.client.SimpleCanalClient;
import com.zz.canal.client.client.transfer.MessageTransponders;
import com.zz.canal.client.config.CanalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCanalClient
public class CanalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class, args);

    }
}
