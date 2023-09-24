package com.zz.canal.client.config;

import com.zz.canal.client.client.CanalClient;
import com.zz.canal.client.client.SimpleCanalClient;
import com.zz.canal.client.client.transfer.MessageTransponders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Describtion: CanalClientConfiguration
 * @Author: 张卫刚
 * @Date: 2023/9/18 20:08
 */
@Component
public class CanalClientConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(CanalClientConfiguration.class);

    @Autowired
    private CanalConfig canalConfig;

//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public BeanUtil beanUtil() {
//        return new BeanUtil();
//    }

    @Bean
    private CanalClient canalClient() {
        CanalClient canalClient = new SimpleCanalClient(canalConfig, MessageTransponders.defaultMessageTransponder());
        canalClient.start();
        logger.info("Starting canal client....");
        return canalClient;
//        return null;
    }
}
