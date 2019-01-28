package cn.eartech.ribbonconsumer.service;

import cn.eartech.ribbonconsumer.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/10 11:57
 */
@Service
public class UserService {
    @Autowired
    private RestTemplate template;

    /**
     * 请求合并
     * @param id
     * @return
     */
    @HystrixCollapser(batchMethod = "findAll",collapserProperties = {@HystrixProperty(name="timerDelayInMillisecond",value = "100")})
    public User findById(Long id){
        return template.getForObject("http://user-service/user/{1}",User.class,id);
    }

    @HystrixCommand
    public List<User> findAll(List<Long> ids){
        return template.getForObject("http://user-service/users?ids={1}",List.class, StringUtils.join(ids,","));
    }
}
