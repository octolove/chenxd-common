package com.cxd.cool.mq.adapter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;

import com.cxd.cool.mq.listener.IMessageListener;

public class CustomerMessageListenerAdapter extends MessageListenerAdapter {

    public CustomerMessageListenerAdapter() {
        super();
    }

    public CustomerMessageListenerAdapter(Object delegate, MessageConverter messageConverter) {
        super(delegate, messageConverter);
    }

    public CustomerMessageListenerAdapter(Object delegate, String defaultListenerMethod) {
        super(delegate, defaultListenerMethod);
    }

    public CustomerMessageListenerAdapter(Object delegate) {
        super(delegate);
    }

    /**
     * 自定义监听器調用自定义的方法
     * 默认使用框架onMessage方法
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected Object invokeListenerMethod(String methodName, Object[] arguments, Message originalMessage) throws Exception {
        if (this.getDelegate() instanceof IMessageListener) {
            IMessageListener<Object, Object> listener = (IMessageListener) this.getDelegate();
            return listener.onMessage(arguments[0]);
        }
        return super.invokeListenerMethod(methodName, arguments, originalMessage);
    }

}
