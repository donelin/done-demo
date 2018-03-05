package com.done;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: done
 * @Date: 2018/3/1 17:27
 * @Description: 启动类
 */
@SpringBootApplication
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.done.javaconfig.*") })
public class Application {


    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        InputStream in = Application.class.getClassLoader().getResourceAsStream("config/properties/springboot.properties");
        properties.load(in);
        SpringApplication app =  new SpringApplication(Application.class);
        app.setDefaultProperties(properties);
        //app.setBannerMode(Banner.Mode.OFF); 设置是否显示banner
        app.run(args);
    }

}
