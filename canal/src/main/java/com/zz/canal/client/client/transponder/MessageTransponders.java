package com.zz.canal.client.client.transponder;

/**
 * @Describtion: MessageTransponder
 * @Author: 张卫刚
 * @Date: 2023/9/17 21:10
 */
public interface MessageTransponders {

    public static TransponderFactory defaultMessageTransponder(){
        return new DefaultTransponderFactory();
    }

}
