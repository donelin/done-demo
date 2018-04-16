package com.done.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Done Lin on 2018/4/8.
 */
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

    private int port;

    private String host;


    public String getHost(){
        return  this.host;
    }

    public void setHost(String host){
        this.host= host;
    }

    public int getPort(){
        return  this.port;
    }

    public void setPort(int port){
        this.port= port;
    }

}
