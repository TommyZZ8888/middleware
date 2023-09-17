package com.zz.canal.client.client;

/**
 * @Describtion: CanalClient
 * @Author: 张卫刚
 * @Date: 2023/9/17 21:35
 */
public interface CanalClient {

    /**
     * open the canal client
     */
    void start();

    /**
     * stop canal client
     */
    void stop();

    /**
     * is running
     * @return
     */
    boolean isRunning();
}
