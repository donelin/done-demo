package com.done.springboot.multiDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Done Lin on 2018/4/21.
 */
//@Configuration
public class SecondaryDataSourceConfig {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db2") // application.properteis中对应属性的前缀
    public DataSource db2() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }


    @Bean
    public DataSourceTransactionManager transactionManager2(@Qualifier("db2")DataSource db2) {
        return new DataSourceTransactionManager(db2);
    }

    @Bean
    public JdbcTemplate db2JdbcTemplate(@Qualifier("db2") DataSource db2){
        return new JdbcTemplate(db2);
    }

}
