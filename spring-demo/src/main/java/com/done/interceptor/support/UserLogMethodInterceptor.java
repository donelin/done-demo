package com.done.interceptor.support;

/**
 * Created by Done Lin on 2018/1/6.
 */

import com.done.model.persist.UserOperationLog;
import com.done.service.UserOperationLogService;
import lombok.extern.log4j.Log4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * 增强（记录用户操作日志）
 */
@Log4j
public class UserLogMethodInterceptor implements MethodInterceptor{

    private ApplicationContext applicationContext;

    public UserLogMethodInterceptor(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        DefaultParameterNameDiscoverer discoverer =  new DefaultParameterNameDiscoverer();
        //获取方法上的参数名数组
        String[] argNames=discoverer.getParameterNames(method);
        //获取方法上的参数值数组
        Object[] argValues= methodInvocation.getArguments();
        UserLog.OperationType type = method.getAnnotation(UserLog.class).value();

        int userIdIndex = ArrayUtils.indexOf(argNames,"userId");
        if(userIdIndex<0){
            log.error("添加用户日志出错：调用["+method.getName()+"]方法，没有传[userId]");
        }
        String userId =(String)argValues[userIdIndex];

        //执行目标方法
        Object result = methodInvocation.proceed();

        try{
            //添加日志
            UserOperationLogService logService = (UserOperationLogService) applicationContext.getBean("userOperationLogService");
            UserOperationLog log = new UserOperationLog(""+System.currentTimeMillis(),userId,type);
            logService.saveUserOperationLog(log);
        }catch (Exception e){
            log.error("添加用户日志出错：调用["+method.getName()+"]方法，没有传[userId]");
        }
        return result;
    }
}