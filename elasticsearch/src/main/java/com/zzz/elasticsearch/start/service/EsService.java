package com.zzz.elasticsearch.start.service;

import com.alibaba.fastjson2.JSON;
import com.zzz.elasticsearch.start.constants.Constant;
import com.zzz.elasticsearch.start.domain.User;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.IndexInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @Describtion: EsService
 * @Author: 张卫刚
 * @Date: 2024/5/19 20:50
 */
@Service
public class EsService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private RestHighLevelClient client;



    public void insert() throws IOException {
        IndexCoordinates indexCoordinates =  IndexCoordinates.of("user");
        boolean exists = elasticsearchOperations.exists("sAOLRZEBPeo7fepiYs37", indexCoordinates);
        System.out.println(exists);
        User user = new User("zhangsan", 22);
        User save = elasticsearchOperations.save(user);
        System.out.println(save.getAge());
    }

    public void insert2() throws IOException {
        User user = new User("zhangsan", 18);
        IndexRequest indexRequest = new IndexRequest(Constant.INDEX);
        IndexRequest source = indexRequest.id("4").source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = client.index(source, RequestOptions.DEFAULT);
        System.out.println(response.getId());
        System.out.println(response.getResult());
    }

    public void update() throws IOException {
        User user = new User("mimi", 18);
        UpdateRequest updateRequest = new UpdateRequest(Constant.INDEX, "qM8ItI8Bc8626luMln0H");
        UpdateRequest request = updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse update = client.update(request,RequestOptions.DEFAULT);
        System.out.println(update.getId());
    }


}
