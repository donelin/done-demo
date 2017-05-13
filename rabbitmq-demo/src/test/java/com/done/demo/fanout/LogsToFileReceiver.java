package com.done.demo.fanout;

import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Done Lin on 2017/5/8.
 */
@Log4j
public class LogsToFileReceiver {

    private final static String EXCHANGE_NAME = "EX_LOG";//交换器名称

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
        /**
         * 声明转发器和类型 可用的转发器类型Direct Topic Headers Fanout
         * Direct Exchange – 处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
         * Fanout Exchange – 不处理路由键。你只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
         * 很像子网广播，每台子网内的主机都获得了一份复制的消息。Fanout交换机转发消息是最快的。
         * Topic Exchange – 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。
         * 因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”。
         */
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        //创建一个非持久的、唯一的且自动删除的队列且队列名称由服务器随机产生一般情况这个名称与amq.gen-JzTY20BRgKO-HjmUJj0wLg 类似。
        String queueName = channel.queueDeclare().getQueue();

        // 为转发器指定队列，设置binding
        channel.queueBind(queueName,EXCHANGE_NAME,"");

        log.info(" [ LogsToFileReceiver ] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 指定接收者，第二个参数为自动应答，无需手动应答
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            this.print2File(message);
        }
    }

    private static void print2File(String msg) {
        try {
            String dir = LogsToFileReceiver.class.getClassLoader().getResource("").getPath();
            String logFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            File file = new File(dir, logFileName + ".txt");
            log.info("文件全路径为："+dir+" "+logFileName);
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write((msg + "\r\n").getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
