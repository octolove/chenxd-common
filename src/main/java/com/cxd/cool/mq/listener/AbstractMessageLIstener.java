package com.cxd.cool.mq.listener;

import com.cxd.cool.mq.config.MessageConfig;

public abstract class AbstractMessageLIstener<K, V> implements IMessageListener<K, V> {

    private MessageConfig messageConfig;

    // 队列名称
    private String QueueName;

    public String getQueueName() {
        return QueueName;
    }

    public void setQueueName(String queueName) {
        QueueName = queueName;
    }

    public void setMessageConfig(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    @Override
    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    /**
     * 请求参数预处理
     * 
     * @param meaage
     * @return
     */
    public K handleMessage(V meaage) {
        try {
            // 调用实现类具体业务方法
            process(meaage);
        } catch (Exception e) {
            // 重试,子类抛异常
        }
        return null;
    }

    /**
     * 记录日志
     */
    @Override
    public K onMessage(V meaage) {
        try {
            handleMessage(meaage);
        } catch (Exception e) {

        } finally {

        }
        return null;
    }

    /**
     * 子类自行实现具体逻辑
     * 
     * @param message
     * @return
     */
    public abstract K process(V message);

}
