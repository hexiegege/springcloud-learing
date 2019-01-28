package cn.eartech.feignconsumer.service;

import cn.eartech.feignconsumer.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2016/9/16.
 */
@Component
public class HelloServiceFallback implements HelloService {

    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello(@RequestParam("name") String name) {
        return "error";
    }

    @Override
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        User user = new User();
        user.setName("未知");
        user.setAge(0);
        return user;
    }

    @Override
    public String hello(@RequestBody User user) {
        return "error";
    }

}
