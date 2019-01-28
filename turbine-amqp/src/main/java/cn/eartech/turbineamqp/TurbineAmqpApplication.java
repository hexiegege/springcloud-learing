package cn.eartech.turbineamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * 开启客户端注册
 * 开启TurbineStream
 */
@EnableEurekaClient
@EnableTurbineStream
@SpringBootApplication
public class TurbineAmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurbineAmqpApplication.class, args);
	}

}

