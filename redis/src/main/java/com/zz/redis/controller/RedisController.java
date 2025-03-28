package com.zz.redis.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * @Describtion: RedisController
 * @Author: 张卫刚
 * @Date: 2025/3/28 14:50
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedissonClient redisson;

	@GetMapping("/test")
	public void test() throws InterruptedException {
		RLock rLock = redisson.getLock("test");
		boolean isLock = rLock.tryLock(10, TimeUnit.SECONDS);
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (isLock) {
				System.out.println(format.format(System.currentTimeMillis()) + "获取分布式锁成功");
				Thread.sleep(1000);
				System.out.println(format.format(System.currentTimeMillis()) + "业务完成");
			} else {
				System.out.println(format.format(System.currentTimeMillis()) + "获取分布式锁失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("业务异常");
		} finally {
			//当前线程未解锁
			if (rLock.isHeldByCurrentThread() && rLock.isLocked()) {
				//释放锁
				System.out.println("解锁");
				rLock.unlock();
			}
		}
	}

	@RequestMapping("/reentrant")
	public void reentrant1() throws InterruptedException {
		//获取锁
		RLock lock = redisson.getLock("reentrant");
		//加锁，参数：获取锁的最大等待时间（期间会重试），锁自动释放时间，时间单位
		boolean isLock = lock.tryLock(10, 25, TimeUnit.SECONDS);
		try {
			if (isLock) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(format.format(System.currentTimeMillis()) + "获取分布式锁1成功");
				Thread.sleep(15000);
				//调用方法2
				reentrant2();
				System.out.println(format.format(System.currentTimeMillis()) + "业务1完成");
			}
		} catch (Exception e) {
			throw new RuntimeException("业务异常");
		} finally {
			//当前线程未解锁
			if (lock.isHeldByCurrentThread() && lock.isLocked()) {
				//释放锁
				System.out.println("分布式锁1解锁");
				lock.unlock();
			}
		}
	}

	/**
	 * 重入方法2
	 *
	 * @throws InterruptedException
	 */
	public void reentrant2() throws InterruptedException {
		//获取锁
		RLock lock = redisson.getLock("reentrant");
		//加锁，参数：获取锁的最大等待时间（期间会重试），锁自动释放时间，时间单位
		boolean isLock = lock.tryLock(5, 25, TimeUnit.SECONDS);
		try {
			if (isLock) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(format.format(System.currentTimeMillis()) + "获取分布式锁2成功");
				Thread.sleep(10000);
				System.out.println(format.format(System.currentTimeMillis()) + "业务2完成");
			}
		} catch (Exception e) {
			throw new RuntimeException("业务异常");
		} finally {
			//当前线程未解锁
			if (lock.isHeldByCurrentThread() && lock.isLocked()) {
				//释放锁
				System.out.println("分布式锁2解锁");
				lock.unlock();
			}
		}
	}
}
