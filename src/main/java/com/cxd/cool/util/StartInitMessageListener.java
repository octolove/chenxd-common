package com.cxd.cool.util;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cxd.cool.domain.RabbitConfigDomain;
import com.cxd.cool.mq.listener.AbstractMessageLIstener;

@Component
public class StartInitMessageListener implements InitializingBean {

    @Autowired
    private RabbitConfigDomain rabbitConfigDomain;

    @Override
    public void afterPropertiesSet() throws Exception {

        if (!CollectionUtils.isEmpty(rabbitConfigDomain.getMessageListeners())) {
            for (ChannelAwareMessageListener messageListener : rabbitConfigDomain.getMessageListeners()) {
                SimpleMessageListenerContainer container1 = new SimpleMessageListenerContainer();
                container1.setConnectionFactory(rabbitConfigDomain.getConnectionFactory());
                container1.setAcknowledgeMode(AcknowledgeMode.AUTO); // 设置确认模式手工确认
                container1.setMaxConcurrentConsumers(1);
                container1.setConcurrentConsumers(1);
                container1.setQueueNames(((AbstractMessageLIstener) messageListener).getQueueName());
                container1.setMessageListener(messageListener);
                container1.start();
            }
        }
    }

}
