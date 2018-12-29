package com.cxd.cool.mq.listener;

public abstract class AbstractMessageLIstener {

    private String QueueName;

    public String getQueueName() {
        return QueueName;
    }

    public void setQueueName(String queueName) {
        QueueName = queueName;
    }
}
