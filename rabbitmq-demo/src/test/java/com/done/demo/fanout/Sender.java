package com.done.demo.fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Done Lin on 2017/5/9.
 */
@Log4j
public class Sender {
    private final static String EXCHANGE_NAME = "EX_LOG";  //交换器名称

    @Test
    public void send() throws IOException {
        log.info("-----------------------------------");
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.74.132");
        // 指定用户 密码
        factory.setUsername("done");
        factory.setPassword("lfx206242");
        // 指定端口 默认5672
        factory.setPort(AMQP.PROTOCOL.PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * 声明转发器和类型 可用的转发器类型Direct Topic Headers Fanout 参考：http://melin.iteye.com/blog/691265
         * Direct Exchange – 处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
         * Fanout Exchange – 不处理路由键。你只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
         * 很像子网广播，每台子网内的主机都获得了一份复制的消息。Fanout交换机转发消息是最快的。
         * Topic Exchange – 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。
         * 因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”。
         */
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = new Date().toLocaleString() + " : log something";
        // 指定消息发送到的转发器
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

        log.info(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
