package com.cxd.cool.mq.listener;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class CommMessageListener extends AbstractMessageLIstener implements ChannelAwareMessageListener {
    
    @PostConstruct
    public void initQueueName() {
        setQueueName("cxd.code");
    }
    
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("-------------CommMessageListener11111111111-----------------");
    }

}
