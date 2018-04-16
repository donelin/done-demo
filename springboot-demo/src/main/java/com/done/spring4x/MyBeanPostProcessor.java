package com.done.spring4x;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by Done Lin on 2018/4/2.
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAssignableFrom(MyDog.class)){
            System.out.println("---------BeanPostProcessor Initialization -----beanName="+beanName+"----------");
            return new MyDogProxy();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAssignableFrom(MyDog.class)) {
            System.out.println("-------BeanPostProcessor AfterInitialization-----beanName=" + beanName + "-------------");
        }
        return bean;
    }
}
