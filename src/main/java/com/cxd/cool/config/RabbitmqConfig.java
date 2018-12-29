package com.cxd.cool.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    /**
     * 死信队列 交换机标识符
     */
    private static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    private static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    /**
     * 死信队列里面消息的超时时间
     */
    private static final String X_MESSAGE_TTL = "x-message-ttl";

    // 普通队列
    @Bean
    public Queue cxdQueue() {
        return new Queue("cxd.code");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("cxdTopicExchange");
    }

    @Bean
    public Binding cxdBinding() {
        return BindingBuilder.bind(cxdQueue()).to(topicExchange()).with("cxd.#");
    }

    // 普通队列2
    @Bean
    public Queue cxdQueue2() {
        return new Queue("cxd2.code2");
    }

    @Bean
    public TopicExchange topicExchange2() {
        return new TopicExchange("cxdTopicExchange2");
    }

    @Bean
    public Binding cxdBinding2() {
        return BindingBuilder.bind(cxdQueue2()).to(topicExchange2()).with("cxd2.#");
    }

    // 延迟队列
    @Bean()
    public Queue userRetryQueue() {
        Map<String, Object> args = new ConcurrentHashMap<>(3);
        // 死信队列exchange
        args.put(DEAD_LETTER_QUEUE_KEY, "cxd.dead.exchange");// //或直接扔到原队列，不使用死信队列
        // 死信队列routekey
        args.put(DEAD_LETTER_ROUTING_KEY, "cxd.dead.#");
        // 死信队列消息超时时间，20秒
        args.put(X_MESSAGE_TTL, 20 * 1000);
        Queue queue = new Queue("cxd.retry", true, false, false, args);
        return queue;
    }

    @Bean
    public TopicExchange userRetryTopicExchange() {
        return new TopicExchange("userRetryTopicExchange");
    }

    @Bean
    public Binding userRetryBinding() {
        return BindingBuilder.bind(userRetryQueue()).to(userRetryTopicExchange()).with("cxd.retry.#");
    }

    // 死信队列
    @Bean
    public Queue deadQueue() {
        return new Queue("cxd.dead");
    }

    @Bean
    public TopicExchange deadExchange() {
        return new TopicExchange("cxd.dead.exchange");
    }

    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("cxd.dead.#");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }
//
//    @Bean
//    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO); // 设置确认模式手工确认
//        container.setMaxConcurrentConsumers(1);
//        container.setConcurrentConsumers(1);
//
//        container.setQueueNames("cxd.code");
//        container.setMessageListener(new CommMessageListener());
//        return container;
//    }
//
//    @Bean
//    public MessageListenerContainer messageListenerContainer2(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO); // 设置确认模式手工确认
//        container.setMaxConcurrentConsumers(1);
//        container.setConcurrentConsumers(1);
//
//        container.setQueueNames("cxd2.code2");
//        container.setMessageListener(new CommMessageListener2());
//        
//        container.start();
//        return container;
//    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        Jackson2JsonMessageConverter jackSonConverter = new Jackson2JsonMessageConverter();
        factory.setMessageConverter(jackSonConverter);
        return factory;
    }
}
