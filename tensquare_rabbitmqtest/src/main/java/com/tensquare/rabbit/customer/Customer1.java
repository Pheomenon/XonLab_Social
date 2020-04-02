package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author:Gao
 * @Date:2020-03-22 10:06
 */
@Component
@RabbitListener(queues = "test")
public class Customer1 {
    @RabbitHandler
    public void getMessage(String msg){
        System.out.println("test"+msg);
    }
}
