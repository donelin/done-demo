package com.done;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: done
 * @Date: 2018/3/1 17:27
 * @Description: 启动类
 */
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.done"})
public class Application {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
