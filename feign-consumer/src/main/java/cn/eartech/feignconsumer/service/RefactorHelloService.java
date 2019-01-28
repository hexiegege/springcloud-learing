package cn.eartech.feignconsumer.service;

import cn.eartech.service.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "HELLO-SERVICE")
public interface RefactorHelloService extends HelloService {

}