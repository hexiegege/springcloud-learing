package cn.eartech.helloservice.controller;

import cn.eartech.helloservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/7 17:47
 */
@RestController
public class HelloController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${spring.application.name}")
    private String serviceId;

    @RequestMapping("/hello")
    public String index(){
        ServiceInstance instance = discoveryClient.getInstances(serviceId).get(0);
        System.out.println(instance.getHost()+instance.getServiceId());
        return "hello,world";
    }
    @GetMapping(value = "/hello1")
    public String hello(@RequestParam String name) {
        ServiceInstance instance = discoveryClient.getInstances(serviceId).get(0);
        System.out.println("/hello1, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "Hello " + name;
    }

    @GetMapping(value = "/hello2")
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        ServiceInstance instance = discoveryClient.getInstances(serviceId).get(0);
        System.out.println("/hello2, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping(value = "/hello3")
    public String hello(@RequestBody User user) {
        ServiceInstance instance = discoveryClient.getInstances(serviceId).get(0);
        System.out.println("/hello3, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "Hello "+ user.getName() + ", " + user.getAge();
    }
}
