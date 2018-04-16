package com.done.spring4x;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Done Lin on 2018/4/2.
 */
public class JeepFactoryBean implements FactoryBean<Jeep> {
    @Override
    public Jeep getObject() throws Exception {
        return new Jeep();
    }

    @Override
    public Class<?> getObjectType() {
        return Jeep.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
