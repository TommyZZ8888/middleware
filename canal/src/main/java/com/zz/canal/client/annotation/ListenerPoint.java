package com.zz.canal.client.annotation;

import java.lang.annotation.*;


/**
 * @Description ListenerPoint
 * @Author 张卫刚
 * @Date Created on 2023/9/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface ListenerPoint {

    String destination() default "";

    String[] schema() default {};

    String[] table() default {};

    CanalEntry.EventType[] eventType() default {};
}
