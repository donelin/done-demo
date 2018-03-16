package com.done.web.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Done Lin on 2018/3/15.
 */
@Aspect
@Component
@Slf4j
public class UserLogAspect {

    @Before("execution(* com.done.dao..*.*(..))")
    private void beforLog(JoinPoint point){
        log.info("======== beforLog ===========");
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        UserLog userLog = method.getAnnotation(UserLog.class);
        log.info("class={},method={},args={}",point.getTarget().getClass(),method.getName(),JSON.toJSONString(point.getArgs()));
        if(userLog!=null){
            log.info("userLog={}",userLog.userIdName()+","+userLog.batch());
        }
    }


    @After("execution(* com.done.dao..*.*(..)) && @annotation(UserLog)")
    private void AfterLog(JoinPoint point){
        log.info("======== AfterLog ===========");
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        UserLog userLog = method.getAnnotation(UserLog.class);
        log.info("class={},method={},args={}",point.getTarget().getClass(),method.getName(),JSON.toJSONString(point.getArgs()));
        log.info("userLog={}",userLog.userIdName()+","+userLog.batch());
    }
}
