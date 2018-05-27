package com.done;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * Created by Done Lin on 2018/5/27.
 */
public class OrderMQProducerDemo {

    private final static String GROUP_NAME = "OrderMQProducerGroup";
    private final static String NAMESRV_ADDR = "192.168.74.133:9876;192.168.74.132:9876";
    private DefaultMQProducer producer;

    public OrderMQProducerDemo() throws MQClientException {
        this.producer = new DefaultMQProducer(GROUP_NAME);
        this.producer.setNamesrvAddr(NAMESRV_ADDR);
        //调用start()方法启动一个producer实例
        this.producer.start();
    }

    public static void main(String[] args) throws Exception {
        OrderMQProducerDemo demo  =new OrderMQProducerDemo();
        for (int i = 1; i <= 32; i++) {
            int queueIndex = 1;
            Message msg = new Message("ORDER-TOPIC", "order_1", "KEY" + i, ("order_1 " + i).getBytes());

            SendResult sendResult = demo.producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id  = (Integer) arg;
                    int index = id % mqs.size();
                    System.out.println(index);
                    return mqs.get(index);
                }
            },queueIndex);
            System.out.println(sendResult);
        }

        for (int i = 1; i <= 32; i++) {
            int queueIndex = 2;
            Message msg = new Message("ORDER-TOPIC", "order_2", "KEY" + i, ("order_2 " + i).getBytes());

            SendResult sendResult = demo.producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id  = (Integer) arg;
                    int index = id % mqs.size();
                    System.out.println(index);
                    return mqs.get(index);
                }
            },queueIndex);
            System.out.println(sendResult);
        }


        for (int i = 1; i <= 32; i++) {
            int queueIndex = 3;
            Message msg = new Message("ORDER-TOPIC", "order_3", "KEY" + i, ("order_3 " + i).getBytes());

            SendResult sendResult = demo.producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id  = (Integer) arg;
                    int index = id % mqs.size();
                    System.out.println(index);
                    return mqs.get(index);
                }
            },queueIndex);
            System.out.println(sendResult);
        }
        //发送完消息之后，调用shutdown()方法关闭producer
        demo.producer.shutdown();

    }
}


