package com.zz.canal.client.event;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @Describtion: CanalEventListener
 * @Author: 张卫刚
 * @Date: 2023/9/18 20:12
 */
public interface CanalEventListener {

    void onEvent(CanalEntry.EventType eventType,CanalEntry.RowData rowData);
}
