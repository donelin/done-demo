package com.done.springboot.autoconfig;

import com.alibaba.fastjson.JSON;
import com.done.redis.EnableRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import redis.clients.jedis.Jedis;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Callable;

/**
 * Created by Done Lin on 2018/4/6.
 */
@SpringBootApplication
@EnableAsync
@Import(MyImportSelector.class)
@EnableLog
@EnableRedis
public class SpringbootDemoApplication {



    public static void  main(String[] args) throws Exception {
        InputStream inputStream = SpringbootDemoApplication.class.getClassLoader().getResourceAsStream("config/springboot.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        SpringApplication app = new SpringApplication(SpringbootDemoApplication.class);
        app.setDefaultProperties(properties);
        ConfigurableApplicationContext context = app.run(args);

        System.out.println(System.getProperty("file.encoding"));
        System.out.println(JSON.toJSONString(context.getBeansOfType(EncodingConverter.class)));
        System.out.println(JSON.toJSONString(context.getBeansOfType(Runnable.class)));
        System.out.println(JSON.toJSONString(context.getBeansOfType(CallBean.class)));
        context.getBean(Callable.class).call();
        System.out.println("==============end===============");

        System.out.println(context.getBean(User.class));
        System.out.println(context.getBean(Role.class));
        System.out.println(context.getBean(LogBean.class));
        System.out.println(context.getBean(Jedis.class));
        context.close();
    }
}
