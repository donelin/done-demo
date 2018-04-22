package com.done.spring4x;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Done Lin on 2018/4/2.
 */
@Component
public class MyApplicationAwareBeanPostProcessor implements BeanPostProcessor {


    @Autowired
    private ApplicationContext context;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof MyApplicationAware){
            MyApplicationAware temp = (MyApplicationAware) bean;
            ((MyApplicationAware) bean).setApplication(context);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
