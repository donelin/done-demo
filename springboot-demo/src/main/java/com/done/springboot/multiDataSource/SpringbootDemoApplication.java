package com.done.springboot.multiDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Done Lin on 2018/4/21.
 *  多数据源配置：
 *  首先要将spring boot自带的DataSourceAutoConfiguration禁掉，
 *  因为它会读取application.properties文件的spring.datasource.*属性并自动配置单数据源。
 *  在@SpringBootApplication注解中添加exclude属性即可：
 *  由于我们禁掉了自动数据源配置，因些下一步就需要手动将这些数据源创建出来
 *
 *
 *  @ServletComponentScan是的spring能够扫描到我们自己编写的servlet和filter。
 */
//@ServletComponentScan
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass=true,exposeProxy=true)
public class SpringbootDemoApplication {



    public static void  main(String[] args) throws IOException {
        InputStream inputStream = SpringbootDemoApplication.class.getClassLoader().getResourceAsStream("config/springboot.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        SpringApplication app = new SpringApplication(SpringbootDemoApplication.class);
        app.setDefaultProperties(properties);
        ConfigurableApplicationContext context = app.run("aa","bb");
//        Map<String,DataSource> map = context.getBeansOfType(DataSource.class);
//        for(Map.Entry<String, DataSource> entry:map.entrySet()){
//            System.out.println(JSON.toJSONString(entry.getKey()+" = "+entry.getValue().getClass()));
//        }
        context.getBean(UserDao.class).save();
//        context.getBean(RoleDao.class).save();
//        context.getBean(ComplexService.class).save();
//        context.getBean(MuliMongodbService.class).save();
        context.close();
    }
}
