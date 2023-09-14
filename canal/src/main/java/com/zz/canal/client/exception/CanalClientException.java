package com.zz.canal.client.exception;

/**
 * @Describtion: CanalClientException
 * @Author: 张卫刚
 * @Date: 2023/9/14 21:03
 */
public class CanalClientException extends RuntimeException{

    public CanalClientException() {
    }

    public CanalClientException(String message) {
        super(message);
    }

    public CanalClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanalClientException(Throwable cause) {
        super(cause);
    }

    public CanalClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

