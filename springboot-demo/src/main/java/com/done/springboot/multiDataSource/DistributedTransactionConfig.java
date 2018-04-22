package com.done.springboot.multiDataSource;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * Created by Done Lin on 2018/4/22.
 *
 * 基于JTA的分布式事务的
 */
@Configuration
public class DistributedTransactionConfig {

    @Value("${spring.datasource.db1.url}")
    private String db1Url;
    @Value("${spring.datasource.db1.username}")
    private String db1Username;
    @Value("${spring.datasource.db1.password}")
    private String db1Password;


    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    @Bean(name="userDataSource", destroyMethod = "close", initMethod="init")
    public DataSource userDataSource() {
        DruidXADataSource  dataSource = new DruidXADataSource();
        dataSource.setUrl(db1Url);
        dataSource.setUsername(db1Username);
        dataSource.setPassword(db1Password);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName("userDataSource");
        return xaDataSource;
    }

    @ConfigurationProperties(prefix = "spring.datasource.db2")
    @Bean(name="roleDataSource", destroyMethod = "close", initMethod="init")
    public DataSource roleDataSource() {
        DruidXADataSource  dataSource = new DruidXADataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/springboot-db2?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername(db1Username);
        dataSource.setPassword(db1Password);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName("roleDataSource");
        return xaDataSource;
    }

    @Bean
    public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource")DataSource userDataSource){
        return new JdbcTemplate(userDataSource);
    }

    @Bean
    public JdbcTemplate roleJdbcTemplate(@Qualifier("roleDataSource")DataSource roleDataSource){
        return new JdbcTemplate(roleDataSource);
    }


    //////////////////////////////////// 配置分布式事务 ////////////////////////////////////////////////

    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(1000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager",initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    public PlatformTransactionManager transactionManager() throws Throwable {
        UserTransaction userTransaction = userTransaction();
        return new JtaTransactionManager(userTransaction,atomikosTransactionManager());
    }


}
