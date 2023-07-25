package com.zz.redis.fivetype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Description SetRedisTest
 * @Author 张卫刚
 * @Date Created on 2023/7/25
 */
@Component
public class SetRedisTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private static final String redisKey = "rediscom";


    public void sAdd(String key,Object... value){
        redisTemplate.opsForSet().add(key,value);
    }

    public Long sLen(String key){
        return redisTemplate.opsForSet().size(key);
    }

    public Object sPop(String key){
       return redisTemplate.opsForSet().pop(redisKey);
    }


    public Set<Object> sGetAll(String key){
        return redisTemplate.opsForSet().members(key);
    }





//    @Bean
//    CommandLineRunner commandLineRunner(){
//        return args -> {
//            redisTemplate.delete(redisKey);
//
//            sAdd(redisKey,1,2,3,4,5);
//
//            System.out.println(sLen(redisKey));
//
//            System.out.println(sPop(redisKey));
//
//            System.out.println(sLen(redisKey));
//
//            sGetAll(redisKey).forEach(System.out::println);
//        };
//    }

}
