package com.zz.software.communication.websocket.rtsp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Describtion: MediaStart
 * @Author: 张卫刚
 * @Date: 2023/10/22 15:40
 */

@Component
public class MediaStart {

    @Autowired
    MediaTransfer mediaTransfer;

    @PostConstruct
    public void init() {
        //异步加载，因为初始化时执行，live里面是死循环监听rtsp,如果不异步操作，就会卡死在初始化阶段，项目就会起不来
        mediaTransfer.live();
    }

}
