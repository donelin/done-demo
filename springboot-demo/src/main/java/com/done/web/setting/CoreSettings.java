package com.done.web.setting;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: done
 * @Date: 2018/3/6 16:18
 */
@Data
@PropertySource("classpath:/config/properties/core.properties")
@ConfigurationProperties(prefix="database")
@Component
public class CoreSettings {

    private String driver;

    private String url;

    private String user;

    private String password;
}
