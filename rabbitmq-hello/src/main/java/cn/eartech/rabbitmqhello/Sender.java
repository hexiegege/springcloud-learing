package cn.eartech.rabbitmqhello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @version 1.0
 * @des: 消息生产者
 * @author: shanfa
 * @date: 2019/1/10 19:35
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){

        String context = "hello"+new Date();
        System.out.println("Sender:"+context);
        rabbitTemplate.convertAndSend("hello",context);
    }
}
