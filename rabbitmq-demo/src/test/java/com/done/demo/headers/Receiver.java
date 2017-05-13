package com.done.demo.headers;

import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.springframework.amqp.core.ExchangeTypes;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Done Lin on 2017/5/8.
 */
@Log4j
public class Receiver {

    private final static String EXCHANGE_NAME = "HEADER-EXCHANGE";//交换器名称
    private final static String QUEUE_NAME = "header-queue";//消息队列

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

        //声明转发器和类型headers
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.HEADERS,false,true,null);
        channel.queueDeclare(QUEUE_NAME,false, false, true,null);

        Map<String, Object> headers = new Hashtable<String, Object>();
        headers.put("x-match", "any");//all any
        headers.put("done", "123456");
        headers.put("bbb", "56789");
        // 为转发器指定队列，设置binding 绑定header键值对
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"", headers);
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 指定接收者，第二个参数为自动应答，无需手动应答
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            log.info(message);
        }
    }
}

