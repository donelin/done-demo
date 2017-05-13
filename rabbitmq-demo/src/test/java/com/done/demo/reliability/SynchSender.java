package com.done.demo.reliability;

import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

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
 * 发送方同步确认
 */
@Log4j
public class SynchSender {
    private final static String QUEUE_NAME = "RELIABILITY-DURABLE"; //消息队列名称

    @Test
    public void send() throws Exception {
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
        String message = "hello world from done! ";


        for(int i=0;i<10000;i++){
            message = i+" : msg  test !!!";
            channel.basicPublish(QUEUE_NAME, QUEUE_NAME,true,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            log.info("Sent Message：'" + message + "'");
            //此处的 if 是为了实现批量confirm 能比较好的提高性能
            if (i>0&&i%100==0){
                if(channel.waitForConfirms(5000)){
                    log.info("success publish msg " + (i-100)+" to "+i);
                }else{
                    log.info("failed publish msg " + (i-100)+" to "+i);
                    channel.basicPublish(QUEUE_NAME, QUEUE_NAME,true,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
                    i-=100;//此处-100是为了重发，也可以先记录下，之后再进行重发
                }
            }
        }


        //关闭频道和资源
        channel.close();
        connection.close();
    }
}
