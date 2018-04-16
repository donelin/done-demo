package com.done.spring4x;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Done Lin on 2018/4/2.
 */
@Configuration
@ComponentScan("com.done.spring4x")
public class MyConfig {

    @Bean
    public MyBean myBean(){
        return new MyBean();
    }

    @Bean
    public JeepFactoryBean jeep(){
        return new JeepFactoryBean();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
        return new MyBeanPostProcessor();
    }
}
