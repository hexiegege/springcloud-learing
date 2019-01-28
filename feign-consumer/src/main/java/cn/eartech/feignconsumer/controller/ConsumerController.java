package cn.eartech.feignconsumer.controller;

import cn.eartech.feignconsumer.entity.User;
import cn.eartech.feignconsumer.service.HelloService;
import cn.eartech.feignconsumer.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/11 10:36
 */
@RestController
public class ConsumerController {
    @Autowired
    private HelloService helloService;
    @Autowired
    private RefactorHelloService refactorHelloService;

    @GetMapping("/feign-consumer")
    public String helloConsumer(){
        return helloService.hello();
    }
    @RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET)
    public String helloConsumer2() {
        User user = new User();
        user.setName("DIDI");
        user.setAge(30);
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("DIDI")).append("\n");
        sb.append(helloService.hello("DIDI", 30)).append("\n");
        sb.append(helloService.hello(user)).append("\n");
        return sb.toString();
    }
    @RequestMapping(value = "/feign-consumer3", method = RequestMethod.GET)
    public String helloConsumer3() {
        cn.eartech.dto.User user = new cn.eartech.dto.User();
        user.setName("DIDI");
        user.setAge(30);
        StringBuilder sb = new StringBuilder();
        sb.append(refactorHelloService.hello("MIMI")).append("\n");
        sb.append(refactorHelloService.hello("MIMI", 20)).append("\n");
        sb.append(refactorHelloService.hello(user)).append("\n");
        return sb.toString();
    }
}
