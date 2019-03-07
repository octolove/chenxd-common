package com.cxd.cool.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
public class RabbitReceiver {
 
    @RabbitListener(queues = "fanout.A")
    public void receiverA(){
        System.out.println("我接收的是fanout.A队列的消息");
    }
 
    @RabbitListener(queues = "fanout.B")
    public void receiverB(){
        System.out.println("我接收的是fanout.B队列的消息");
    }
 
    @RabbitListener(queues = "fanout.C")
    public void receiverC(){
        System.out.println("我接收的是fanout.C队列的消息");
    }
 
}