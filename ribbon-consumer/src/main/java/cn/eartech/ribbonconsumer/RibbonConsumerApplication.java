package cn.eartech.ribbonconsumer;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author shanfa
 */
@SpringBootApplication
@EnableDiscoveryClient
// @EnableCircuitBreaker 也是开启断路器
@EnableHystrix // 开启断路器
public class RibbonConsumerApplication {

	/**
	 * 添加ribbon负载均衡
	 * @return
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerApplication.class, args);
	}

}

