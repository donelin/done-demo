package com.done.app;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.AbstractAdaptableMessageListener;

/**
 * Created by Done Lin on 2018/9/2.
 */
@Log4j
public class SpringTestConsumer extends AbstractAdaptableMessageListener {


    private  int i;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        Thread.sleep(1000);
        logger.info(Thread.currentThread()+ "收到消息i=:"+i+" "+new String(message.getBody())+" == "+JSON.toJSONString(message));
        i++;
        if(i>5){
            throw  new RuntimeException("测试重试次数");
        }
    }
}
