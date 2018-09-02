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

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        logger.info("收到消息:"+JSON.toJSONString(message));
        Thread.sleep(5000);
    }
}
