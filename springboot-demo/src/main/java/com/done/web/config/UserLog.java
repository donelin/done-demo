package com.done.web.config;

import java.lang.annotation.*;

/**
 * Created by Done Lin on 2018/3/15.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLog {

    String userIdName() default "userId";


    boolean batch() default true;
}
