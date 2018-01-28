package com.done.interceptor;

import com.done.interceptor.support.UserLog;
import com.done.interceptor.support.UserLogMethodInterceptor;
import lombok.Setter;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: Done Lin
 * @Date: 2018/1/6.
 * @Description: 日志AOP拦截器（用注解AOP实现用户日志记录）
 */
public class UserLogAdvisor extends DefaultPointcutAdvisor implements InitializingBean,ApplicationContextAware {

    @Setter
    private ApplicationContext applicationContext;

    public UserLogAdvisor(){
        super();
        this.setOrder(Integer.MIN_VALUE+1);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setPointcut(new AnnotationMatchingPointcut(null,UserLog.class));
        this.setAdvice(new UserLogMethodInterceptor(applicationContext));
    }

}

