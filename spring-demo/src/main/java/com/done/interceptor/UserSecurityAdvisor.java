package com.done.interceptor;

import com.done.service.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * @Author: Done Lin
 * @Date: 2018/1/6.
 * @Description: 用户权限AOP拦截器（只有id为789654123的用户才能添加新的用户）
 */
public class UserSecurityAdvisor extends StaticMethodMatcherPointcutAdvisor implements MethodInterceptor {

    public UserSecurityAdvisor(){
        super();
        /**
         * 因为UserSecurityAdvisor实现了MethodInterceptor接口，
         * 所以自己本身就是一个advice，
         * 所以只要把自己当作增强（advice)设置进行就可以了
         */
        this.setAdvice(this);
        this.setOrder(Integer.MIN_VALUE);
    }

    /**
     * 匹配类（只有UserService才进行拦截）
     * @return
     */
    @Override
    public ClassFilter  getClassFilter(){
        return  new ClassFilter() {
            @Override
            public boolean matches(Class<?> targetClass) {
                return UserService.class.isAssignableFrom(targetClass);
            }
        };
    }

    /**
     * 只有saveXXX方法才进行拦截
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().contains("save");
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        DefaultParameterNameDiscoverer discoverer =  new DefaultParameterNameDiscoverer();
        //获取方法上的参数名数组
        String[] argNames=discoverer.getParameterNames(method);
        //获取方法上的参数值数组
        Object[] argValues= methodInvocation.getArguments();

        int userIdIndex = ArrayUtils.indexOf(argNames,"userId");
        Assert.isTrue(userIdIndex>=0);
        String userId =(String)argValues[userIdIndex];
        if(userId.equals("789654123")){
            return methodInvocation.proceed();
        }
        throw new RuntimeException("调用["+method.getName()+"]方法,userId=["+userId+"]无权限进行操作");
    }
}

