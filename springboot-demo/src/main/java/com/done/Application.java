package com.done;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: done
 * @Date: 2018/3/1 17:27
 * @Description: 启动类
 */
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.done"})
public class Application {


    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        InputStream in = Application.class.getClassLoader().getResourceAsStream("config/properties/springboot.properties");
        properties.load(in);
        SpringApplication app =  new SpringApplication(Application.class);
        app.setDefaultProperties(properties);
        app.run();
        //app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}
