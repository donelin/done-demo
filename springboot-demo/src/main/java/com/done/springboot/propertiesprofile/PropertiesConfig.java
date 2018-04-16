package com.done.springboot.propertiesprofile;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by Done Lin on 2018/4/6.
 */
@Configuration
public class PropertiesConfig implements InitializingBean{

    @Autowired
    private Environment environment;

    @Value("${spring.datasource.password1:9000}")
    private String password;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(environment.getProperty("spring.datasource.username"));
        System.out.println(password);
    }
}
