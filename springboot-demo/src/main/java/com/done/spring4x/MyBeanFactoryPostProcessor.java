package com.done.spring4x;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 在bean初始化(构造函数方法执行)之前执行的，容器只会执行一次
 * Created by Done Lin on 2018/4/2.
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("---------BeanFactoryPostProcessor postProcessBeanFactory----------");
    }
}
