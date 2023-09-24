package com.zz.canal.client.annotation;

import com.zz.canal.client.config.CanalClientConfiguration;
import com.zz.canal.client.config.CanalConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Describtion: EnableCanalClient
 * @Author: 张卫刚
 * @Date: 2023/9/14 20:53
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CanalConfig.class, CanalClientConfiguration.class})
public @interface EnableCanalClient {
}
