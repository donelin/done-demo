package com.done.demo.rpc;
import java.security.MessageDigest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created by Done Lin on 2017/5/13.
 *
 * RPC调用服务端
 */
@Log4j
public class RPCServer {
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    @Test
    public void receive() throws Exception {
        // 先建立连接、通道，并声明队列
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.74.132");
        factory.setUsername("done");
        factory.setPassword("lfx206242");
        factory.setPort(AMQP.PROTOCOL.PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME,true , false, false, null);
        // 可以运行多个服务器进程。通过channel.basicQos设置prefetchCount属性可将负载平均分配到多台服务器上。
        channel.basicQos(1);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //打开应答机制autoAck=false
        channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
        log.info(" [x] Awaiting RPC requests");

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            BasicProperties props = delivery.getProperties();
            BasicProperties replyProps = new BasicProperties.Builder().correlationId(props.getCorrelationId()).build();
            String message = new String(delivery.getBody());
            log.info("接收到消息：" + message);//处理逻辑业务
            message+=" done!";
            //String response = getMd5String(message);
            //返回处理结果队列
            channel.basicPublish("", props.getReplyTo(), replyProps,message.getBytes());
            log.info("发送消息：" + message);
            //发送应答
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    // 模拟RPC方法 获取MD5字符串
    public static String getMd5String(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}