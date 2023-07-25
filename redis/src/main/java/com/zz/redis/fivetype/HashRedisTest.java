package com.zz.redis.fivetype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description HashRedisTest
 * @Author 张卫刚
 * @Date Created on 2023/7/24
 */

@Component
public class HashRedisTest {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void hSet(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public Object hGet(String key,String hashKey){
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    public List<Object> hGetAll(String key){
        return redisTemplate.opsForHash().values(key);
    }

    public Set<Object> hKeys(String key){
        return redisTemplate.opsForHash().keys(key);
    }

    public Map<Object,Object> hEntries(String key){
        return redisTemplate.opsForHash().entries(key);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(){
//        return args ->
//        {
//            Set<Object> o = hKeys("myinfo");
//            System.out.println(o.toString());
//        };
//    }

}
