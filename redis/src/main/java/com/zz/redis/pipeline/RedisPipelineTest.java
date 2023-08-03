package com.zz.redis.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description RedisPipelineTest
 * @Author 张卫刚
 * @Date Created on 2023/7/26
 */
//@Component
public class RedisPipelineTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void setExPipeline(Map<String, String> map, long expireTime) {
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            for (Map.Entry<String, String> entry : map.entrySet()) {
//                stringSerializer.serialize(entry.getKey())
                connection.setEx(stringSerializer.serialize(entry.getKey()), expireTime,
                        stringSerializer.serialize(entry.getValue()));
//                connection.setEx(entry.getKey().getBytes(), expireTime,
//                        entry.getValue().getBytes());
            }
            return null;
        });
    }

    @SuppressWarnings("unchecked")
    public void setSessionPipeline(Map<String, String> map, long expireTime) {
        redisTemplate.executePipelined(new SessionCallback<>() {
            @Override
            public Object execute(@Nullable RedisOperations operations) throws DataAccessException {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    assert operations != null;
                    operations.opsForValue().set(entry.getKey(), entry.getValue(), expireTime, TimeUnit.SECONDS);
                }
                return null;
            }
        });
    }



    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
//            Map<String, String> map = new HashMap<>();
//            for (int i = 0; i < 1000; i++) {
//                map.put("name" + i, "stefanie");
//            }
//
//            setSessionPipeline(map, 60);

            Set<String> name = redisTemplate.keys("name[0-9]*");
            for (String s : name) {
                redisTemplate.delete(s);
            }

            StopWatch stopWatch = new StopWatch("redisPipeline");
            Map<String, String> map = new HashMap<>();
            List<String> list = new ArrayList<>();

            stopWatch.start("init key");
            for (int i = 0; i < 1000; i++) {
                map.put("name" + i, "stefanie");
                list.add("name" + i);
            }
            redisTemplate.opsForValue().multiSet(map);
            stopWatch.stop();

            stopWatch.start("multiGet");
            List<Object> objects1 = redisTemplate.opsForValue().multiGet(list);
            stopWatch.stop();

            stopWatch.start("pipeline");
            List<Object> objects = redisTemplate.executePipelined((RedisCallback<String>) connection -> {
                for (String redisKey : list) {
                    connection.get(redisKey.getBytes());
                }
                return null;
            });
            stopWatch.stop();

            System.out.println(stopWatch.prettyPrint());
            System.out.println("pipeLineResult => " + objects1.size());
            System.out.println("multiGetResult => " + objects.size());
            objects.forEach(System.out::println);
        };
    }
}
