package cn.eartech.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringCloudApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	/**
	 * 请求过滤
	 * @return
	 */
	@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}

	/**
	 * 自定义路由映射规则
	 * @return
	 */
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		return new PatternServiceRouteMapper(
				"(?<name>^.+)-(?<version>v.+$)",
				"${version}/${name}");
	}

}

