package com.done.springboot.propertiesprofile;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

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
        ConfigurableApplicationContext context = app.run(args);

        ConfigurableEnvironment env =  context.getEnvironment();
        String datasourceurl = env.getProperty("spring.datasource.url");
        System.out.println(datasourceurl);
        System.out.println(JSON.toJSON(args));
        CorePropertiesConfig pro = context.getBean(CorePropertiesConfig.class);
        pro.show();
        String platformId = env.getProperty("platformId");
        System.out.println(platformId);
        String jdbc = env.getProperty("jdbc");
        System.out.println(jdbc);
        System.out.println(JSON.toJSONString(context.getBeansOfType(ProfileBean.class)));

        context.close();
    }
}
