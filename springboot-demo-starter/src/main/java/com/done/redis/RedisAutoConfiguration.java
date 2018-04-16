package com.done.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * Created by Done Lin on 2018/4/8.
 */
//@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnClass(Jedis.class)
public class RedisAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public Jedis jedis(RedisProperties redisProperties){
        return new Jedis(redisProperties.getHost(),redisProperties.getPort());
    }
}
