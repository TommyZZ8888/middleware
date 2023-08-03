package com.zz.redis.distributedlock;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Description RedisLock2
 * @Author 张卫刚
 * @Date Created on 2023/8/3
 */
public class RedisLock2 {

    public static final String CACHE_SHOPSTYPE_LIST = "cache:shopsType:";

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1");
        return flag;
    }

    private void unLock(String key) {
        stringRedisTemplate.delete(key);
    }


    public String queryWithMutex(Long id) {
        String key = "cache_shop_key" + id;

        String shopJson = stringRedisTemplate.opsForValue().get(key);

        if (!StringUtil.isNullOrEmpty(shopJson)) {
            return shopJson;
        }

        if (shopJson != null) {
            return null;
        }
        try {
            boolean isLock = tryLock("lock:shop:" + id);

            if (!isLock) {
                Thread.sleep(60);
                return queryWithMutex(id);
            }

            // 4.3. 获取成功，重建缓存
            Thread.sleep(200); // 模拟重建延迟

            // 4.3.1. 模拟查询数据库
            shopJson = "value";
            // 4.3.1.1 数据库不存在 —— 将空值写入缓存（缓存穿透解决方案），返回 null
            if (shopJson == null) {
                stringRedisTemplate.opsForValue().set(key, "", 2L, TimeUnit.MINUTES);
                return null;
            }

            // 4.3.1.2 数据库存在 —— 写入 Redis ，然后返回，实现缓存重建
            stringRedisTemplate.opsForValue().set(key, shopJson, 30L, TimeUnit.MINUTES);


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            unLock("lock:shop:" + id);
        }
        return shopJson;
    }
}
