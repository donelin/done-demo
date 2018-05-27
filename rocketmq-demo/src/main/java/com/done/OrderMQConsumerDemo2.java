package com.done;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Done Lin on 2018/5/27.
 */
public class OrderMQConsumerDemo2 {

    private final static String GROUP_NAME = "OrderMQConsumerGroup";
    private final static String NAMESRV_ADDR = "192.168.74.133:9876;192.168.74.132:9876";

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup(GROUP_NAME);
        consumer.setNamesrvAddr(NAMESRV_ADDR);

        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe("ORDER-TOPIC", "*");

        /**
         * 实现了MessageListenerOrderly表示一个队列只会被一个线程取到
         *，第二个线程无法访问这个队列
         */
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs.get(0).getTags() + " "+ msgs.get(0).getKeys()+" "+ new String(msgs.get(0).getBody()));
                    TimeUnit.SECONDS.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
        System.out.println("OrderConsumer2 Started.");
    }
}
