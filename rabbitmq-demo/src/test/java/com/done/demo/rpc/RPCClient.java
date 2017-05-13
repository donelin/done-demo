package com.done.demo.rpc;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created by Done Lin on 2017/5/13.
 *
 * RPC调用客户端
 */
@Log4j
public class RPCClient {
    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";//rpc队列
    private String replyQueueName;//回调队列
    private QueueingConsumer consumer;


    @Test
    public void run() throws Exception {
        RPCClient client = new RPCClient();
        String response = client.call("abc");
        client.close();
    }

    public RPCClient() throws Exception {
        // 先建立一个连接和一个通道，并为回调声明一个唯一的'回调'队列
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.74.132");
        factory.setUsername("done");
        factory.setPassword("lfx206242");
        factory.setPort(AMQP.PROTOCOL.PORT);
        connection = factory.newConnection();
        channel = connection.createChannel();
        //  注册'回调'队列，这样就可以收到RPC响应
        replyQueueName = channel.queueDeclare().getQueue();
        consumer = new QueueingConsumer(channel);
        channel.basicConsume(replyQueueName, true, consumer);
    }

    //发送RPC请求
    public String call(String message) throws Exception {
        String response = null;
        String corrId = java.util.UUID.randomUUID().toString();
        //发送请求消息，消息使用了两个属性：replyto和correlationId
        BasicProperties props = new BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();
        log.info("发送消息："+message);
        channel.basicPublish("", requestQueueName, props, message.getBytes());
        //等待接收结果
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            //检查它的correlationId是否是我们所要找的那个
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response = new String(delivery.getBody());
                log.info("回调队列接收到消息："+response);
                break;
            }
        }
        return response;
    }
    public void close() throws Exception {
        connection.close();
    }
}
