package com.done.springboot.multiDataSource;

import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Done Lin on 2018/4/22.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MultipleMongoProperties {
    private MongoProperties first = new MongoProperties();
    private MongoProperties second = new MongoProperties();
}
