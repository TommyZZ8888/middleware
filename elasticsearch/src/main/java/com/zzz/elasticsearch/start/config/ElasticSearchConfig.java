package com.zzz.elasticsearch.start.config;

import lombok.Setter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description ElasticSearchConfig
 * @Author 张卫刚
 * @Date Created on 2023/8/31
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfig {

    private int port;

    private String host;

    private int connTimeout;

    private int socketTimeout;

    private int connectionRequestTimeout;


    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(host, port))
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                        .setConnectTimeout(connTimeout)
                        .setSocketTimeout(socketTimeout)
                        .setConnectionRequestTimeout(connectionRequestTimeout));
        return new RestHighLevelClient(restClientBuilder);
    }
}
