package com.cxd.cool.mq.listener;

import com.cxd.cool.mq.config.MessageConfig;

/**
 * 自定义监听器
 * 
 * @param <K> 返回值
 * @param <V> 请求值
 */
public interface IMessageListener<K, V> {

    public MessageConfig getMessageConfig();

    public String getQueueName();

    public K onMessage(V meaage);

}
