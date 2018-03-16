package com.done.web.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: done
 * @Date: 2018/3/13 13:47
 */
@Slf4j
//@Component
public class Jeep implements InitializingBean {




    private Aeep aeep;



    public Jeep(){
        log.info("==================Jeep construcor ==================");
    }

    @Autowired
    public void setAeep(Aeep aeep){
        this.aeep=aeep;
        log.info("==================Jeep setProperty ==================");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("==================Jeep afterPropertiesSet ==================");
    }

}
