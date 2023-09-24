package com.zz.canal.client.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Description CannalEventListener
 * @Author 张卫刚
 * @Date Created on 2023/9/14
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface CanalEventListener {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
