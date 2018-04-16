package com.done.spring4x;

import org.springframework.context.ApplicationContext;

/**
 * Created by Done Lin on 2018/4/5.
 */
public interface MyApplicationAware {

    public void setApplication(ApplicationContext context);
}
