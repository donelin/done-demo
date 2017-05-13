package com.done.demo.workqueue;

import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created by Done Lin on 2017/5/8.
 */
@Log4j
public class ReceiverTwo {
    private final static String QUEUE_NAME = "WORKQUEUE-DURABLE"; //消息队列名称

    @Test
    public void receive() throws Exception {
        //区分不同工作进程的输出
        int hashCode = ReceiverTwo.class.hashCode();
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

        QueueingConsumer consumer = new QueueingConsumer(channel);

        /**
         * ack= true: Round-robin 转发   消费者被杀死，消息会丢失
         * ack=false:消息应答 ，为了保证消息永远不会丢失，RabbitMQ支持消息应答（message acknowledgments）。
         * 消费者发送应答给RabbitMQ，告诉它信息已经被接收和处理，然后RabbitMQ可以自由的进行信息删除。
         * 如果消费者被杀死而没有发送应答，RabbitMQ会认为该信息没有被完全的处理，然后将会重新转发给别的消费者。
         * 通过这种方式，你可以确认信息不会被丢失，即使消者偶尔被杀死。
         * 消费者需要耗费特别特别长的时间是允许的。
         *
         */
        boolean ack = false; //打开应答机制
        // 指定消费队列
        channel.basicConsume(QUEUE_NAME, ack, consumer);

        //公平转发  设置最大服务转发消息数量    只有在消费者空闲的时候会发送下一条信息。
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            log.info(hashCode + " Received Message：'" + message + "'");
            doWork(message);
            log.info(hashCode + " Received Done");
            //发送应答
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }

    /**
     * 每个点耗时1s
     *
     * @param task
     * @throws InterruptedException
     */
    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.')
                Thread.sleep(1000);
        }
    }
}
