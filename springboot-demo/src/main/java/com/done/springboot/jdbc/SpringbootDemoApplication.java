package com.done.springboot.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Done Lin on 2018/4/6.
 */
@SpringBootApplication
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
        System.out.println(context.getBean(DataSource.class).getClass());
        //context.getBean(UserDAO.class).save("linxia");
        System.out.println(context.getBean("userService").getClass());
        ((IUserService)context.getBean("userService")).testAop();
        context.close();
    }
}
