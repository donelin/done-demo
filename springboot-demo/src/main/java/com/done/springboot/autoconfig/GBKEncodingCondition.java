package com.done.springboot.autoconfig;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by Done Lin on 2018/4/7.
 */
public class GBKEncodingCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String encoding = System.getProperty("file.encoding");
        if("GBK".equals(encoding)){
            return true;
        }
        return false;
    }
}
