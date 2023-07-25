package com.zz.redis.fivetype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Description ZSetRedisTest
 * @Author 张卫刚
 * @Date Created on 2023/7/25
 */

@Component
public class ZSetRedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String redisKey = "rediscom";


    public void zAdd(String key,Object value,Double score){
        redisTemplate.opsForZSet().add(key,value,score);
    }

    public Long zLen(String key){
        return redisTemplate.opsForZSet().size(key);
    }


    public Set<Object> zGetAll(String key,long start,long end){
        return redisTemplate.opsForZSet().range(key,start,end);
    }





//    @Bean
//    CommandLineRunner commandLineRunner(){
//        return args -> {
//            redisTemplate.delete(redisKey);
//
//            zAdd(redisKey,1,1.0);
//            zAdd(redisKey,2,1.0);
//            zAdd(redisKey,3,1.0);
//            zAdd(redisKey,4,1.0);
//            zAdd(redisKey,5,1.0);
//
//            Long aLong = zLen(redisKey);
//
//            System.out.println(aLong);
//
//            zGetAll(redisKey,0,aLong).forEach(System.out::println);
//        };
//    }

}
