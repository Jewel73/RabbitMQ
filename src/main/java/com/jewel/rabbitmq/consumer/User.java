package com.jewel.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.jewel.rabbitmq.config.MessageConfig;
import com.jewel.rabbitmq.model.OrderStatus;

@Component
public class User {

    @RabbitListener(queues = MessageConfig.QUEUE)
    public void hello(OrderStatus orderStatus) {
        System.out.println("Message recieved from queue : " + orderStatus);
    }
}
