package com.done.demo.direct;

import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created by Done Lin on 2017/5/8.
 */
@Log4j
public class ErrorLogReceiver {

    private static final String EXCHANGE_NAME = "EX_LOGS_DIRECT";//交换器名称
    private static final String ROUTINGKEY = "error"; //routingKey

    @Test
    public void receive() throws Exception {
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
        // 声明direct类型转发器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String queueName = channel.queueDeclare().getQueue();
        // 指定binding_key
        channel.queueBind(queueName, EXCHANGE_NAME, ROUTINGKEY);
        log.info(" [*] Waiting for " + ROUTINGKEY + " logs. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            log.info(" [x] Received '" + message + "'");
        }
    }
}

