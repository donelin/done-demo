package com.done.springboot.jdbc;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by Done Lin on 2018/4/8.
 */
@Aspect
@Component
public class UserServiceAspect {

    @Around(value = "execution(* com.done.springboot.jdbc.UserServiceImpl.*(..))")
    public Object  around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("==========before========");
        Object result = jp.proceed();
        System.out.println("==========after========");
        return result;
    }

}
