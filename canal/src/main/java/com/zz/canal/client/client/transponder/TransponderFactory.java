package com.zz.canal.client.client.transponder;

import com.alibaba.otter.canal.client.CanalConnector;
import com.zz.canal.client.annotation.CanalEventListener;
import com.zz.canal.client.client.ListenerPoint;
import com.zz.canal.client.config.CanalConfig;

import java.util.List;
import java.util.Map;

/**
 * @Describtion: TransponderFactory
 * @Author: 张卫刚
 * @Date: 2023/9/17 21:11
 */
public interface TransponderFactory {

    MessageTransponder newTransponder(CanalConnector connector, Map.Entry<String, CanalConfig.Instance> config,
                                      List<CanalEventListener> listeners, List<ListenerPoint> annoListeners);

}
