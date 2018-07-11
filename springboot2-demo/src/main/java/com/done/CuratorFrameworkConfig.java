package com.done;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Done Lin on 2018/4/2.
 * 创建zookeeper客户端
 */
//@Configuration
public class CuratorFrameworkConfig {

    @Bean
    public CuratorFramework client(){
        CuratorFramework client =  CuratorFrameworkFactory
                .newClient("192.168.74.132:2183,192.168.74.132:2181,192.168.74.132:2182",
                        new ExponentialBackoffRetry(3000, 3));
        client.start();
        return client;
    }
}
