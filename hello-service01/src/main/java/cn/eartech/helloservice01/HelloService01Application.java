package cn.eartech.helloservice01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 选用的注册中心是eureka，那么就推荐@EnableEurekaClient，
 * 如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient。
 */
@SpringBootApplication
@EnableEurekaClient   //Eureka 作为注册中心，开启客户端
//@EnableDiscoveryClient  // 通用 其他（zookeeper等）
public class HelloService01Application {

	public static void main(String[] args) {
		SpringApplication.run(HelloService01Application.class, args);
	}

}

