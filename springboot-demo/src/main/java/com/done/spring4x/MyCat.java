package com.done.spring4x;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Done Lin on 2018/4/5.
 */
@Component
public class MyCat implements MyApplicationAware{

    private ApplicationContext context;

    @Override
    public void setApplication(ApplicationContext context) {
        this.context = context;
    }

    public ApplicationContext  getContext(){
         return context;
    }
}
