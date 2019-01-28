package cn.eartech.feignconsumer;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author shanfa
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class FeignConsumerApplication {
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignConsumerApplication.class, args);
	}

}

