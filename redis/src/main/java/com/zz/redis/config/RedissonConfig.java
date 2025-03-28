package com.zz.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Describtion: RedisSessionConfig
 * @Author: 张卫刚
 * @Date: 2025/3/28 9:59
 */
@Configuration
public class RedissonConfig {

	@Bean
	public RedissonClient redisSessionClient() {
		// 单体
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		return Redisson.create(config);

		//主从
//        Config config = new Config();
//        config.useMasterSlaveServers()
//            .setMasterAddress("redis://127.0.0.1:6379").setPassword("123456")
//            .addSlaveAddress("redis://127.0.0.1:6389")
//            .addSlaveAddress("redis://127.0.0.1:6399");
//        return Redisson.create(config);

		//哨兵
//        Config config = new Config();
//        config.useSentinelServers()
//            .setMasterName("myMaster")
//            .addSentinelAddress("redis://127.0.0.1:6379", "redis://127.0.0.1:6389")
//            .addSentinelAddress("redis://127.0.0.1:6399");
//        return Redisson.create(config);

		//集群
//        Config config = new Config();
//        config.useClusterServers()
//                //cluster state scan interval in milliseconds
//            .setScanInterval(2000)
//            .addNodeAddress("redis://127.0.0.1:6379", "redis://127.0.0.1:6389")
//            .addNodeAddress("redis://127.0.0.1:6399");
//        return Redisson.create(config);
	}


}
