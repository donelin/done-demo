package com.done.demo.workqueue;

import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Done Lin on 2017/5/8.
 */
@Log4j
public class Sender {
    private final static String QUEUE_NAME = "WORKQUEUE-DURABLE"; //消息队列名称

    @Test
    public void send() throws IOException {
        //创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.74.132");
        //指定用户 密码
        factory.setUsername("done");
        factory.setPassword("lfx206242");
        //指定端口 默认5672
        factory.setPort(AMQP.PROTOCOL.PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        boolean durable = true; //设置消息持久化  RabbitMQ不允许使用不同的参数重新定义一个队列，所以已经存在的队列，我们无法修改其属性。
        //声明队列
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

        //发送10条消息，依次在消息后面附加1-10个点
        for (int i = 5; i > 0; i--)
        {
            String dots = "";
            for (int j = 0; j <= i; j++)
            {
                dots += ".";
            }
            String message = "hello world from done! " + dots + dots.length();
            //MessageProperties.PERSISTENT_TEXT_PLAIN 标识我们的信息为持久化的
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            log.info("Sent Message：'" + message + "'");
        }
        //关闭频道和资源
        channel.close();
        connection.close();
    }
}
