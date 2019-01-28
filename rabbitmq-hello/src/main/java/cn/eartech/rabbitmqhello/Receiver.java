package cn.eartech.rabbitmqhello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @des:  消息消费者
 * @author: shanfa
 * @date: 2019/1/10 19:38
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {
    @RabbitHandler
    private void process(String msg){

        System.out.println("Receiver:"+msg);
    }
}
