package com.done.springboot.extend;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Done Lin on 2018/4/7.
 * 容器执行refresh之前的回调
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("===== displayName = "+applicationContext.getDisplayName());
    }
}
