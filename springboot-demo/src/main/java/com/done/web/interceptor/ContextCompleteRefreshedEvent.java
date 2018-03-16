package com.done.web.interceptor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author: done
 * @Date: 2018/3/15 14:31
 */
public class ContextCompleteRefreshedEvent implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[]  names = context.getBeanDefinitionNames();

    }
}
