package com.done.springboot.autoconfig;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Done Lin on 2018/4/7.
 */
@Component
public class Jeep implements Callable {



    @Async
    @Override
    public Object call() {
        for(int i = 0;i<5;i++){
            System.out.println("---"+i+"---");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
