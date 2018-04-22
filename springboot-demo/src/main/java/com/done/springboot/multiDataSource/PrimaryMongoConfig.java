package com.done.springboot.multiDataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Done Lin on 2018/4/22.
 */
@Configuration
@EnableMongoRepositories(basePackages = {"com.done.springboot.multiDataSource.model.repository.primary"},
        mongoTemplateRef = PrimaryMongoConfig.MONGO_TEMPLATE)
public class PrimaryMongoConfig {
    protected static final String MONGO_TEMPLATE = "primaryMongoTemplate";
}
