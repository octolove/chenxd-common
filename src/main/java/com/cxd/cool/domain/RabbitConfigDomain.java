package com.cxd.cool.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

public class RabbitConfigDomain {

    private ConnectionFactory connectionFactory;

    private List<ChannelAwareMessageListener> messageListeners;

    public RabbitConfigDomain() {
        messageListeners = new ArrayList<>();
    }

    public void setMessageListeners(List<ChannelAwareMessageListener> messageListeners) {
        this.messageListeners = messageListeners;
    }

    public List<ChannelAwareMessageListener> getMessageListeners() {
        return messageListeners;
    }

    public void addMessageListener(ChannelAwareMessageListener messageListener) {
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
