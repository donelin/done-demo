package com.done.springboot.propertiesprofile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Done Lin on 2018/4/6.
 */
@Configuration
public class ProfileConfig {


    @Bean
    @Profile("test")
    public ProfileBean testProfile(){
        return new ProfileBean();
    }

    @Bean
    @Profile("prod")
    public ProfileBean prodProfile(){
        return new ProfileBean();
    }


}
