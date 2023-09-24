package com.zz.canal;

import com.zz.canal.client.client.CanalClient;
import com.zz.canal.client.client.SimpleCanalClient;
import com.zz.canal.client.client.transfer.MessageTransponders;
import com.zz.canal.client.config.CanalConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class CannalApplicationTests {

    @Autowired
    private CanalConfig canalConfig;



    @Test
    void contextLoads() {
        Map<String, CanalConfig.Instance> instances = canalConfig.getInstances();

        CanalClient canalClient = new SimpleCanalClient(canalConfig, MessageTransponders.defaultMessageTransponder());
        canalClient.start();
    }

}
