package com.done.spring4x;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 在bean的属性初始化之后执行（每个bean的都会经过这个后置处理器，有多少个bean就会这些多少次）
 * Created by Done Lin on 2018/4/2.
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在bean的初始化(构造函数方法执行)之后并且属性赋值()之后,init方法（@PostConstruct 方法和 InitializingBean的afterPropertiesSet()方法）之前执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAssignableFrom(MyDog.class)){
            System.out.println("---------BeanPostProcessor Initialization -----beanName="+beanName+"----------");
            //return new MyDogProxy();
        }
        return bean;
    }

    /**
     * 在bean的init方法（@PostConstruct 方法和 InitializingBean的afterPropertiesSet()方法）之后执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof MyDog) {
            System.out.println("-------BeanPostProcessor AfterInitialization-----beanName=" + beanName + "-------------");
        }
        return bean;
    }
}
