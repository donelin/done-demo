package com.done.redis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by Done Lin on 2018/4/8.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisAutoConfiguration.class)
public @interface EnableRedis {
}
