package com.done.springboot.propertiesprofile;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Done Lin on 2018/4/6.
 */
@PropertySource(value = {"classpath:/config/core.properties"})
@ConfigurationProperties(prefix = "quartz")
@Configuration
@Setter
public class CorePropertiesConfig {

    private String instanceName;
    private String className;
    private int threadCount;
    private int threadPriority;

    public void show(){
        System.out.println(this.instanceName);
    }
}
