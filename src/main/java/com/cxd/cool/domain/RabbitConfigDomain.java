package com.cxd.cool.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.cxd.cool.mq.listener.IMessageListener;

public class RabbitConfigDomain {

    private ConnectionFactory connectionFactory;

    private List<IMessageListener> messageListeners;

    public RabbitConfigDomain() {
        messageListeners = new ArrayList<>();
    }

    public void setMessageListeners(List<IMessageListener> messageListeners) {
        this.messageListeners = messageListeners;
    }

    public List<IMessageListener> getMessageListeners() {
        return messageListeners;
    }

    public void addMessageListener(IMessageListener messageListener) {
        if (messageListener != null) {
            messageListeners.add(messageListener);
        }
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

}
