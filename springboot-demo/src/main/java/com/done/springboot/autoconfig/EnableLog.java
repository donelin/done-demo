package com.done.springboot.autoconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by Done Lin on 2018/4/7.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LogImportSelector.class)
public @interface EnableLog {
}
