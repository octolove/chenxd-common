package com.cxd.cool.kaf;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.annotation.KafkaListener;

public class Consumer {
    @KafkaListener(topics = { "test" })
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("---->" + record);
            System.out.println("---->" + message);

        }

    }
}
