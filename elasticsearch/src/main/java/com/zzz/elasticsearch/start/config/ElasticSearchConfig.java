package com.zzz.elasticsearch.start.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description ElasticSearchConfig
 * @Author 张卫刚
 * @Date Created on 2023/8/31
 */
//@Configuration //使用yml
public class ElasticSearchConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9500, "http")
                )
        );
        return client;
    }
}
