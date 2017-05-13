package com.done.demo.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Done Lin on 2017/5/9.
 * 主题类型的转发器背后的逻辑和直接类型的转发器很类似：一个附带特殊的选择键将会被转发到绑定键与之匹配的队列中。需要注意的是：关于绑定键有两种特殊的情况。*可以匹配一个标识符。
 * #可以匹配0个或多个标识符。
 */
@Log4j
public class Sender {
    private static final String EXCHANGE_NAME = "TOPIC_LOGS";        //交换器名称

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
        // 声明转发器的类型
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        //定义绑定键
        String[] routingKeys = new String[] { "kernal.info", "cron.warning","auth.info", "kernal.critical" };
        for (String routingKey : routingKeys)
        {
            //发送4条不同绑定键的消息
            String msg = UUID.randomUUID().toString();
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
            log.info(" [x] Sent routingKey = "+routingKey+" ,msg = " + msg + ".");
        }
        channel.close();
        connection.close();
    }
}
