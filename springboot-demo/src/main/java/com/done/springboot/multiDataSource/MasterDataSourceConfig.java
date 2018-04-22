package com.done.springboot.multiDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

/**
 * Created by Done Lin on 2018/4/21.
 */
//@Configuration
public class MasterDataSourceConfig {

    /**
     * 数据源配置对象
     * Primary 表示这个是主数据源
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.db1") // application.properteis中对应属性的前缀
    public DataSource db1() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean
    @Primary
    public JdbcTemplate db1JdbcTemplate(@Qualifier("db1")DataSource db1){
        return new JdbcTemplate(db1);
    }

    @Bean
    @Primary
    public DataSourceTransactionManager  transactionManager1(@Qualifier("db1")DataSource db1) {
        return new DataSourceTransactionManager(db1);
    }
}
