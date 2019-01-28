package cn.eartech.ribbonconsumer.command;

import cn.eartech.ribbonconsumer.entity.User;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;



/**
 * 自定义断路器同步异步响应方式 重载实现（还可以用注解方式实现）
 * 调用方式及注解实现见HelloService
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/9 18:05
 */
public class UserCommand extends HystrixCommand<User> {
    private RestTemplate template;
    private Long id;

    public UserCommand(Setter setter,RestTemplate template, Long id) {
        super(setter);
        this.template = template;
        this.id = id;
    }
    @Override
    protected User run(){
        return template.getForObject("http://user-service/user/{1}",User.class,id);
    }

    /**
     *  服务降级
     */
    @Override
    protected User getFallback() {
        return new User();
    }

    /**
     * 开启缓存
     * @return
     */
    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }
}
