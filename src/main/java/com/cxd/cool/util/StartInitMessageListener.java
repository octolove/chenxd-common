package com.cxd.cool.util;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.cxd.cool.domain.RabbitConfigDomain;
import com.cxd.cool.mq.adapter.CustomerMessageListenerAdapter;
import com.cxd.cool.mq.listener.IMessageListener;

/**
 * 可以换到config中配置
 * 初始化MQ监听器
 */
public class StartInitMessageListener implements InitializingBean {

    @Autowired
    private RabbitConfigDomain rabbitConfigDomain;

    // 封装
    private MessageListenerAdapter buildAdapter(IMessageListener<?, ?> consumer) {
        MessageListenerAdapter messageListenerAdapter = new CustomerMessageListenerAdapter(consumer);
        // messageListenerAdapter.setMessageConverter(messageConverter);
        messageListenerAdapter.setDefaultListenerMethod("onMessage");
        messageListenerAdapter.setEncoding("utf-8");
        return messageListenerAdapter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (!CollectionUtils.isEmpty(rabbitConfigDomain.getMessageListeners())) {
            for (IMessageListener<?, ?> messageListener : rabbitConfigDomain.getMessageListeners()) {
                SimpleMessageListenerContainer container1 = new SimpleMessageListenerContainer();
                container1.setConnectionFactory(rabbitConfigDomain.getConnectionFactory());
                container1.setAcknowledgeMode(AcknowledgeMode.AUTO); // 设置确认模式手工确认MANUAL
                container1.setMaxConcurrentConsumers(50);
                container1.setConcurrentConsumers(5);
                container1.setPrefetchCount(10);
                container1.setQueueNames(messageListener.getQueueName());
                container1.setMessageListener(buildAdapter(messageListener));
                container1.start();
            }
        }
    }

}
