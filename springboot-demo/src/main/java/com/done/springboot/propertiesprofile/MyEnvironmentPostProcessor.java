package com.done.springboot.propertiesprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Done Lin on 2018/4/6.
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor  {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            System.out.println("================MyEnvironmentPostProcessor=======================");
            InputStream inputStream = MyEnvironmentPostProcessor.class.getClassLoader().getResourceAsStream("config/system.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            PropertySource sources = new PropertiesPropertySource("system",properties);
            environment.getPropertySources().addLast(sources);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
