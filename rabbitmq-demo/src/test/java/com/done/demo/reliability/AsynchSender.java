package com.done.demo.reliability;

import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Done Lin on 2017/5/11.
 * 如果设置channel为confirm状态，则通过该channel发送的消息都会被分配一个唯一的ID，
 * 然后一旦该消息被正确的路由到匹配的队列中后，服务器会返回给生产者一个Confirm，该Confirm包含该消息的ID，
 * 这样生产者就会知道该消息已被正确分发。对于持久化消息，只有该消息被持久化后，才会返回Confirm。
 * Confirm机制的最大优点在于异步，生产者在发送消息以后，即可继续执行其他任务。
 * 而服务器返回Confirm后，会触发生产者的回调函数，生产者在回调函数中处理Confirm信息。
 * 如果消息服务器发生异常，导致该消息丢失，会返回给生产者一个nack，
 * 表示消息已经丢失，这样生产者就可以通过重发消息，保证消息不丢失。
 * Confirm机制在性能上要比事务优越很多。但是Confirm机制，无法进行回滚，
 * 就是一旦服务器崩溃，生产者无法得到Confirm信息，生产者其实本身也不知道该消息吃否已经被持久化，
 * 只有继续重发来保证消息不丢失，但是如果原先已经持久化的消息，并不会被回滚，
 * 这样队列中就会存在两条相同的消息，系统需要支持去重。可以mandatory配合实现消息的发送可靠性。
 *
 *
 * 发送方异步确认
 */
@Log4j
public class AsynchSender {
    private final static String QUEUE_NAME = "RELIABILITY-DURABLE"; //消息队列名称

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
        channel.confirmSelect();//设置信道为confirm状态
        String message = "hello world from done ! ";
        channel.addConfirmListener(new ConfirmListener() {
            /**
             * 异步的确认机制是一种事后发现机制，并不能同步的发现问题
             * @param deliveryTag
             * @param multiple
             * @throws IOException
             */
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {//失败重发
                /**
                 * 异步流程：
                 *   1 发送数据并返回(不确认rabbitmq服务器已成功接收)
                 *   2 异步的接收从rabbitmq返回的ack确认信息
                 *   3 收到ack后调用confirmCallback函数
                  *  注意：在confirmCallback中是没有原message的，所以无法在这个函数中调用重发，confirmCallback只有一个通知的作用
                 */
                log.info("发送失败！deliveryTag"+" = "+deliveryTag+" multiple = "+multiple);
            }
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {//确认ok
                log.info("发送成功！deliveryTag"+" = "+deliveryTag+" multiple = "+multiple);
            }
        });
        AMQP.BasicProperties properties = MessageProperties.PERSISTENT_TEXT_PLAIN;
        properties.setAppId("100000");
        /**
         * mandatory：当mandatory标志位设置为true时，
         * 如果exchange根据自身类型和消息routeKey无法找到一个符合条件的queue，
         * 那么会调用basic.return方法将消息返还给生产者；当mandatory设为false时，
         * 出现上述情形broker会直接将消息扔掉
         */
        /**
         * immediate：当immediate标志位设置为true时，
         * 如果exchange在将消息route到queue(s)时发现对应的queue上没有消费者，
         * 那么这条消息不会放入队列中。
         * 当与消息routeKey关联的所有queue(一个或多个)都没有消费者时，
         * 该消息会通过basic.return方法返还给生产者
         */
        channel.basicPublish("", QUEUE_NAME,true,false,properties, message.getBytes());
        log.info("Sent Message：'" + message + "'");

        //关闭频道和资源
        channel.close();
        connection.close();
    }
}
