package com.cxd.cool.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;

import com.cxd.cool.domain.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// @Component
// @RabbitListener(queues = "cxd.dead",containerFactory="rabbitListenerContainerFactory")
public class OrderListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public void getPromotion(String value) {

        System.out.println("-------value----------" + value);

        // 重新回到原队列
        //rabbitTemplate.setMessageConverter();
        rabbitTemplate.convertAndSend("cxdTopicExchange", "cxd.1", value + ".122");

        //rabbitTemplate.getMessageConverter().fromMessage()
        // ChannelAwareMessageListener
        //SimpleMessageListenerContainer

    }

    @RabbitHandler
    public void getPromotion(UserInfo info) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("-----------------" + mapper.writeValueAsString(info));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
