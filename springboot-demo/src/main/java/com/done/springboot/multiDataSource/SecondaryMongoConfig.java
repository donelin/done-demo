package com.done.springboot.multiDataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Done Lin on 2018/4/22.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.done.springboot.multiDataSource.model.repository.secondary",
        mongoTemplateRef = SecondaryMongoConfig.MONGO_TEMPLATE)
public class SecondaryMongoConfig {
    protected static final String MONGO_TEMPLATE = "secondaryMongoTemplate";
}
