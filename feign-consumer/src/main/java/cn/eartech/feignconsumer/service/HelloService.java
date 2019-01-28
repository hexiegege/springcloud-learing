package cn.eartech.feignconsumer.service;

import cn.eartech.feignconsumer.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/11 10:34
 */
@FeignClient(name="HELLO-SERVICE",fallback = HelloServiceFallback.class)
public interface HelloService {
    @RequestMapping("/hello")
    String hello();
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name) ;

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    String hello(@RequestBody User user);
}
