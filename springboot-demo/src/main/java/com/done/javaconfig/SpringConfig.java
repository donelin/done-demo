package com.done.javaconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Done Lin on 2018/3/3.
 */
@Configuration //通过该注解来表明该类是一个Spring的配置，相当于一个xml文件
@ComponentScan(basePackages = "com.done.javaconfig") //配置扫描包
@PropertySource(value = {"classpath:config/properties/core.properties"})
public class SpringConfig {

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.driver}")
    private String databaseDriver;

    @Value("${database.user}")
    private String databaseUser;

    @Value("${database.password}")
    private String databasePassword;


    @Bean // 通过该注解来表明是一个Bean对象，相当于xml中的<bean>
    public UserDAO getUserDAO(){
        return new UserDAO(); // 直接new对象做演示
    }

    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName("default");
        dataSource.setUrl(databaseUrl);
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUsername(databaseUser);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

}
