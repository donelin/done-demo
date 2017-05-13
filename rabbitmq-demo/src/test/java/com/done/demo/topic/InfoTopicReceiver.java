package com.done.demo.topic;

import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created by Done Lin on 2017/5/8.
 */
@Log4j
public class InfoTopicReceiver {

    private static final String EXCHANGE_NAME = "TOPIC_LOGS";//交换器名称

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
        // 声明转发器
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 随机生成一个队列
        String queueName = channel.queueDeclare().getQueue();
        // 接收所有与kernel相关的消息
        channel.queueBind(queueName, EXCHANGE_NAME, "*.info");

        log.info(" [*] Waiting for critical messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true)
        {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();
            log.info(" [x] Received routingKey = " + routingKey+ ",msg = " + message + ".");
        }
    }
}

