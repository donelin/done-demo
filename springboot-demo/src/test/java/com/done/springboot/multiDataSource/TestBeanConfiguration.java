package com.done.springboot.multiDataSource;

import lombok.Data;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by Done Lin on 2018/4/22.
 */
@TestConfiguration
public class TestBeanConfiguration {

    @Bean
    public TestBean testBean(){
        return new TestBean();
    }
}

@Data
class TestBean{
    private String id="1234213241241";
}
