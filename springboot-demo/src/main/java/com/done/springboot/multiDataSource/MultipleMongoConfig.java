package com.done.springboot.multiDataSource;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by Done Lin on 2018/4/22.
 */
@Configuration
public class MultipleMongoConfig {

    @Primary
    @Bean(name = PrimaryMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate primaryMongoTemplate(@Qualifier("primaryFactory") MongoDbFactory primaryFactory) throws Exception {
        return new MongoTemplate(primaryFactory);
    }

    @Bean
    @Qualifier(SecondaryMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate secondaryMongoTemplate(@Qualifier("secondaryFactory") MongoDbFactory secondaryFactory) throws Exception {
        return new MongoTemplate(secondaryFactory);
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(MultipleMongoProperties mongoProperties) throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(mongoProperties.getFirst().getHost(),
                mongoProperties.getFirst().getPort()),
                mongoProperties.getFirst().getDatabase());
    }

    @Bean
    public MongoDbFactory secondaryFactory(MultipleMongoProperties mongoProperties) throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(mongoProperties.getSecond().getHost(),
                mongoProperties.getSecond().getPort()),
                mongoProperties.getSecond().getDatabase());
    }
}