package com.done.app;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.tools.json.JSONReader;
import com.rabbitmq.tools.json.JSONUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Done Lin on 2017/5/13.
 */
@Log4j
public class Consumer {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring.xml");
        Thread.sleep(1000000);
        System.out.println("----------- 进程结束 ---------------");
    }
}
