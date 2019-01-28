package cn.eartech.helloservice01.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/7 17:47
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index(){
        return "hello,world";
    }
}
