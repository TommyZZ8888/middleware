package com.zz.canal.client.event;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.Objects;

/**
 * @Describtion: DmlCanalEventListener
 * @Author: 张卫刚
 * @Date: 2023/9/18 20:14
 */
public interface DmlCanalEventListener extends CanalEventListener {


    @Override
    default void onEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        Objects.requireNonNull(eventType);
        switch (eventType) {
            case INSERT:
                onInsert(rowData);
                break;
            case UPDATE:
                onUpdate(rowData);
                break;
            case DELETE:
                onDelete(rowData);
                break;
            default:
                break;
        }
    }


    void onInsert(CanalEntry.RowData rowData);

    void onUpdate(CanalEntry.RowData rowData);

    void onDelete(CanalEntry.RowData rowData);
}
