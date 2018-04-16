package com.done.spring4x;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by Done Lin on 2018/4/2.
 */
@Component
public class MyDog implements InitializingBean,DisposableBean {


    public MyDog(){
        System.out.println("-------MyDog constructor------");
    }


    private MyBean bean;

    @Autowired
    public void setBean(MyBean bean){
        bean=bean;
        System.out.println("-------MyDog setBean------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--------MyDog InitializingBean---------");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("-------MyDog DisposableBean--------");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("---------MyDog @PostConstruct---------");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("---------MyDog  @PreDestroy----------");
    }
}
