package cn.eartech.rabbitmqhello;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @version 1.0
 * @des: RabbitMQ配置类
 * @author: shanfa
 * @date: 2019/1/10 19:41
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloQueue(){
        System.out.println("RabbitMQ配置类");
        return new Queue("hello");
    }
}
