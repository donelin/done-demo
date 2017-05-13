package com.done.demo.helloword;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Done Lin on 2017/5/8.
 */
@Log4j
public class Sender {
    private final static String QUEUE_NAME = "DEMO-HELLOWORD";//消息队列名称

    @Test
    public void send() throws IOException {
        log.info("-----------------------------------");
        // 创建连接连接到MabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost("192.168.74.132");
        //指定用户 密码
        factory.setUsername("done");
        factory.setPassword("lfx206242");
        //指定端口 默认5672
        factory.setPort(AMQP.PROTOCOL.PORT);
        //创建一个连接
        Connection connection = factory.newConnection();
        //创建一个频道
        Channel channel = connection.createChannel();
        //指定一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发送的消息
        String message = "hello world from done!";
        //往队列中发出一条消息
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        log.info("Sent Message：'" + message + "'");
        //关闭频道和连接
        channel.close();
        connection.close();
    }
}
