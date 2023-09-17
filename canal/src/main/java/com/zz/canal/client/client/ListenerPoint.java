package com.zz.canal.client.client;

import com.zz.canal.client.annotation.ListenPoint;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describtion: ListenerPoint
 * @Author: 张卫刚
 * @Date: 2023/9/17 21:31
 */
public class ListenerPoint {
    private Object target;

    private Map<Method, ListenPoint> invokeMap = new HashMap<>();

    ListenerPoint(Object target, Method method, ListenPoint anno){
        this.target = target;
        this.invokeMap.put(method,anno);
    }

    public Object getTarget() {
        return target;
    }

    public Map<Method, ListenPoint> getInvokeMap() {
        return invokeMap;
    }
}
