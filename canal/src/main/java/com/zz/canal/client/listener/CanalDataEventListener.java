package com.zz.canal.client.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zz.canal.client.annotation.*;
import com.zz.canal.client.annotation.CanalEventListener;

import java.util.List;

/**
 * @Describtion: CanalDataEventListener
 * @Author: 张卫刚
 * @Date: 2023/9/17 22:14
 */
@CanalEventListener
public class CanalDataEventListener {
    /**
     * 数据增量同步
     * 实现业务逻辑
     */
    @UpdateListenPoint
    public void test(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        //数据变更前
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        for (CanalEntry.Column column : beforeColumnsList) {
            System.out.println(column.getName() + ":" + column.getValue());
        }
        //数据变更后
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : afterColumnsList) {
            System.out.println(column.getName() + ":" + column.getValue());
        }
    }

    @InsertListenPoint
    public void test1(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        //数据变更前
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        for (CanalEntry.Column column : beforeColumnsList) {
            System.out.println(column.getName() + ":" + column.getValue());
        }
    }

    @DeleteListenPoint
    public void test2(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        //数据变更前
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        for (CanalEntry.Column column : beforeColumnsList) {
            System.out.println(column.getName() + ":" + column.getValue());
        }
    }

    /**
     * destination 指定监听实例
     * schema	指定监听数据库名称
     * table	指定表名 {"user"} || {"user","user_info"}
     * eventType 指定监听CURD类型
     */
    @ListenPoint(destination = "example", schema = "数据库名", table = {"表名1", "表名2"}
            , eventType = {CanalEntry.EventType.INSERT, CanalEntry.EventType.UPDATE})
    public void test3(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {

    }

}
