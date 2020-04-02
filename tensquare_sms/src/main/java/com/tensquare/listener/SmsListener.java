package com.tensquare.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author:Gao
 * @Date:2020-03-22 14:23
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @RabbitHandler
    public void executeSms(Map<String,String> map){
        System.out.println("手机号： "+map.get("mobile"));
        System.out.println("验证码： "+map.get("randomNumber"));
    }
}
