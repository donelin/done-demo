package com.done;

import com.done.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass=true,exposeProxy=true)
@Slf4j
public class Application {



    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        InputStream in = Application.class.getClassLoader().getResourceAsStream("config/properties/springboot.properties");
        properties.load(in);
        SpringApplication app =  new SpringApplication(Application.class);
        app.setDefaultProperties(properties);
        ConfigurableApplicationContext context = app.run(args);
        //log.info("=============== BeanDefinition={}",context.getBeanFactory().getBeanDefinition("aeep").getBeanClassName());
//      DataSource dataSource = (DataSource) context.getBean("dataSource");
//      String[] beanNames = context.getBeanDefinitionNames();
//      log.info("dataSource={}",dataSource);
//      log.info("beanNames={}", JSON.toJSONString(beanNames));
     //   UserDAO dao = (UserDAO) context.getBean("userDAO");
    //    dao.save("linfeng","linxia");
    //    context.close();
    }

}
