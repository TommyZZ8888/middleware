package com.zz.redis.lua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @Description LuaAcriptTest
 * @Author 张卫刚
 * @Date Created on 2023/7/27
 */
//@Component
public class LuaScriptTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static DefaultRedisScript<Long> redisScript;

    static {
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        Resource resource = new ClassPathResource("limit.lua");
        ResourceScriptSource resourceScriptSource = new ResourceScriptSource(resource);
        redisScript.setScriptSource(resourceScriptSource);
    }


    public Long solution1(String redisKey, Object... args) {
        return redisTemplate.execute(redisScript, Collections.singletonList(redisKey), args);
    }


    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            System.out.println(solution1("name",2,"stefanie",5,"sun",300));
        };
    }
}
