package com.done.springboot.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Done Lin on 2018/4/7.
 */
@Configuration
public class MyConfig {

    @Bean
    @Conditional(GBKEncodingCondition.class)
    public EncodingConverter GBKConverter(){
        return new GBKConverter();
    }


    @Bean
    @Conditional(UTF8EncodingCondition.class)
    public EncodingConverter UTF8Converter(){
        return new UTF8Converter();
    }


    @Bean
    @ConditionalOnProperty(name="condition.runnable.active",havingValue = "true")
    public Runnable thread(){
        return  ()->{};
    }

    @Bean
    @ConditionalOnBean(Runnable.class)
    public CallBean call(){
        return new CallBean();
    }
}
