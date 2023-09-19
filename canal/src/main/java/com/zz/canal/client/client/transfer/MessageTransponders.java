package com.zz.canal.client.client.transfer;

/**
 * @Describtion: MessageTransponder
 * @Author: 张卫刚
 * @Date: 2023/9/17 21:10
 */
public class MessageTransponders {

    public static TransponderFactory defaultMessageTransponder(){
        return new DefaultTransponderFactory();
    }

}
