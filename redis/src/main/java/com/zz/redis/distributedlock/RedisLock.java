package com.zz.redis.distributedlock;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Description RedisLock
 * @Author 张卫刚
 * @Date Created on 2023/7/28
 */
//@Component
public class RedisLock {

    Logger logger = LoggerFactory.getLogger(RedisLock.class);


    //    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final int DEFAULT_ACQUIRE_RESOLUTIONS_MILLS = 100;

    private String lockKey;

    private int expireMsecs = 60 * 1000;

    private int timeoutMsecs = 10 * 1000;

    private volatile boolean locked = false;

    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey, int expireMsecs, int timeoutMsecs) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.expireMsecs = expireMsecs;
        this.timeoutMsecs = timeoutMsecs;
    }

    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey, int expireMsecs) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.expireMsecs = expireMsecs;
    }

    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
    }

    private String getLockKey() {
        return lockKey;
    }


    public String get(String key) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer stringSerializer = new StringRedisSerializer();
                    byte[] data = connection.get(stringSerializer.serialize(key));
                    connection.close();
                    if (data == null) {
                        return null;
                    }
                    return stringSerializer.deserialize(data);
                }
            });
        } catch (Exception e) {
            logger.error("get redis error, key : {}", key);
        }

        return obj == null ? null : obj.toString();
    }


    public boolean setNx(String key, String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                    Boolean success = connection.setNX(stringRedisSerializer.serialize(key), stringRedisSerializer.serialize(value));
                    connection.close();
                    return success;
                }
            });
        } catch (Exception e) {
            logger.error("setNx redis error,key: {},value: {}", key, value);
        }

        return obj != null && (boolean) obj;
    }

    public String getSet(String key, String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                    byte[] data = connection.getSet(stringRedisSerializer.serialize(key), stringRedisSerializer.serialize(value));
                    connection.close();
                    return stringRedisSerializer.deserialize(data);
                }
            });
        } catch (Exception e) {
            logger.error("getSet redis error,key: {},value: {}", key, value);
        }

        return obj != null ? obj.toString() : null;
    }

    /**
     * 获得 lock.
     * 实现思路: 主要是使用了redis 的setnx命令,缓存了锁.
     * reids缓存的key是锁的key,所有的共享, value是锁的到期时间(注意:这里把过期时间放在value了,没有时间上设置其超时时间)
     * 执行过程:
     * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
     * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
     * @return true if lock is acquired, false acquire timeouted
     * @throws InterruptedException in case of thread interruption
     */
    public synchronized boolean lock() throws InterruptedException {
        int timeout = timeoutMsecs;
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + expireMsecs + 1;
            String expireStr = String.valueOf(expires);
            if (setNx(lockKey, expireStr)) {
                locked = true;
                return true;
            }
            String currentValueStr = this.get(lockKey);
            if (currentValueStr != null && (Long.parseLong(currentValueStr) < System.currentTimeMillis())) {
                String oldValueStr = this.getSet(lockKey, expireStr);

                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    locked = true;
                    return true;
                }
            }
            timeout -= DEFAULT_ACQUIRE_RESOLUTIONS_MILLS;

            Thread.sleep(DEFAULT_ACQUIRE_RESOLUTIONS_MILLS);
        }
        return false;
    }

    /**
     * Acquired lock release
     */
    public synchronized void unlock() {
        if (locked) {
            redisTemplate.delete(lockKey);
            locked = false;
        }
    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            RedisLock lock = new RedisLock(redisTemplate, "key", 10000, 20000);
            try {
                if (lock.lock()) {
                    //需要加锁的代码
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
                //操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
                lock.unlock();
            }
        };
    }
}
