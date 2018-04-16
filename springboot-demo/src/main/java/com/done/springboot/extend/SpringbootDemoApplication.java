package com.done.springboot.extend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Done Lin on 2018/4/6.
 */
@SpringBootApplication
public class SpringbootDemoApplication {



    public static void  main(String[] args) throws IOException {
        InputStream inputStream = SpringbootDemoApplication.class.getClassLoader().getResourceAsStream("config/springboot.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        SpringApplication app = new SpringApplication(SpringbootDemoApplication.class);
        app.setDefaultProperties(properties);
        ConfigurableApplicationContext context = app.run("aa","bb");
        context.close();
    }
}
