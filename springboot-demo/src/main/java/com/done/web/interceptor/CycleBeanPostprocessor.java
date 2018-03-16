package com.done.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: done
 * @Date: 2018/3/13 11:34
 */
@Slf4j
@Component
public class CycleBeanPostprocessor implements BeanDefinitionRegistryPostProcessor {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        log.info("================== beanDefinitionRegistry ==================");
        String[] beanNames = beanDefinitionRegistry.getBeanDefinitionNames();
        for(String s:beanNames){
            if ("jeep".equals(s)){
                log.info(s+" ======= "+beanDefinitionRegistry.getBeanDefinition(s).getBeanClassName());
            }
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info("================== postProcessBeanFactory ==================");
    }
}
