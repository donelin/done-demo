package com.done;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;


public class DefaultMQProducerDemo {
	
	private final static String GROUP_NAME = "MQProducerGroup";
	private final static String NAMESRV_ADDR = "192.168.74.133:9876;192.168.74.132:9876";
	private DefaultMQProducer producer;
	
	public DefaultMQProducerDemo() throws MQClientException {
		this.producer = new DefaultMQProducer(GROUP_NAME);
		this.producer.setNamesrvAddr(NAMESRV_ADDR);
        //调用start()方法启动一个producer实例
        this.producer.start();
	}


	public static void  main(String[] args) throws Exception {
		DefaultMQProducerDemo demo = new DefaultMQProducerDemo();
        for(int i = 0;i<50;i++){
            Message message = new Message();
            message.setTopic("NORMAL-TOPIC");
            //若发送的tag为*,则消费者只能以*来接收
            String tag = "tag2";
            message.setTags(tag);
            message.setBody("hello done!".getBytes());
            message.setKeys(tag);
            SendResult result =  demo.producer.send(message);
            System.out.println(JSON.toJSON(result));
        }
        //发送完消息之后，调用shutdown()方法关闭producer
        demo.producer.shutdown();
    }
}