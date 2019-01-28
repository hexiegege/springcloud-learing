package cn.eartech.helloservice.controller;


import cn.eartech.dto.User;
import cn.eartech.service.HelloService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactorHelloController implements HelloService {

	@Override
	public String hello(@RequestParam("name") String name) {
		return "Hello " + name;
	}

	@Override
	public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		User user = new User();
		user.setName(name);
		user.setAge(age);
		return user;
	}

	@Override
	public String hello(@RequestBody User user) {
		return "Hello "+ user.getName() + ", " + user.getAge();
	}

}