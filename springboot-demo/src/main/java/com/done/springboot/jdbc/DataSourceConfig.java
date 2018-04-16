package com.done.springboot.jdbc;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Done Lin on 2018/4/7.
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.driverClassName}")
    private String databaseDriver;

    @Value("${spring.datasource.username}")
    private String databaseUser;

    @Value("${spring.datasource.password}")
    private String databasePassword;

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
