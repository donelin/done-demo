package com.done.spring4x;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Done Lin on 2018/4/2.
 */
@Component
public class MyBeanDefinitionRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(MyPig.class);
        for(int i=0;i<10;i++){
            builder.addPropertyValue("name","admin"+i);
            registry.registerBeanDefinition("myPig"+i,builder.getBeanDefinition());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
