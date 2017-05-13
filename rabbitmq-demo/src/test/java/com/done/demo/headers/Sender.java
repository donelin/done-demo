package com.done.demo.headers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Done Lin on 2017/5/9.
 *
 * Headers类型的exchange使用的比较少，它也是忽略routingKey的一种路由方式。
 * 是使用Headers来匹配的。Headers是一个键值对，可以定义成Hashtable。
 * 发送者在发送的时候定义一些键值对，接收者也可以再绑定时候传入一些键值对，
 * 两者匹配的话，则对应的队列就可以收到消息。匹配有两种方式all和any。
 * 这两种方式是在接收端必须要用键值"x-mactch"来定义。
 * all代表定义的多个键值对都要满足，而any则代码只要满足一个就可以了。
 * fanout，direct，topic exchange的routingKey都需要要字符串形式的，
 * 而headers exchange则没有这个要求，因为键值对的值可以是任何类型
 */
@Log4j
public class Sender {
    private final static String EXCHANGE_NAME = "HEADER-EXCHANGE";//交换器名称

    @Test
    public void send() throws IOException {
        //创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.74.132");
        // 指定用户 密码
        factory.setUsername("done");
        factory.setPassword("lfx206242");
        // 指定端口 默认5672
        factory.setPort(AMQP.PROTOCOL.PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明转发器和类型headers
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.HEADERS,false,true,null);
        String message = new Date().toLocaleString() + " : log something";

        Map<String,Object> headers =  new Hashtable<String, Object>();
        headers.put("done", "123456");
        Builder properties = new AMQP.BasicProperties.Builder();
        properties.headers(headers);

        // 指定消息发送到的转发器,绑定键值对headers键值对
        channel.basicPublish(EXCHANGE_NAME, "",properties.build(),message.getBytes());

        log.info("Sent message :'" + message + "'");
        channel.close();
        connection.close();
    }
}
