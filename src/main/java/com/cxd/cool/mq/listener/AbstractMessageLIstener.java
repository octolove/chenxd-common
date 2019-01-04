package com.cxd.cool.mq.listener;

public abstract class AbstractMessageLIstener {

    //队列名称
    private String QueueName;

    public String getQueueName() {
        return QueueName;
    }

    public void setQueueName(String queueName) {
        QueueName = queueName;
    }
}
