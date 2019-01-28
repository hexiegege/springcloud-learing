package cn.eartech.ribbonconsumer.service;

import cn.eartech.ribbonconsumer.command.UserCommand;
import cn.eartech.ribbonconsumer.entity.User;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/8 19:21
 */
@Service
public class HelloService {
    @Autowired
    private RestTemplate template;

    com.netflix.hystrix.HystrixCommand.Setter setter;

    /**
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "HelloFallBack")
    @CacheResult  // 设置请求缓存
    public String HelloService(){
        return template.getForObject("http://hello-cn.eartech.helloserviceapi.service/hello",String.class);
    }

    /**
     * 第一次服务降级
     * 命令名称，分组 线程池划分
     * commandKey = "",groupKey = "",threadPoolKey = ""
     * @return
     */
    @HystrixCommand(fallbackMethod = "HelloFallBackTwice",commandKey = "",groupKey = "",threadPoolKey = "")
    public String HelloFallBack(){
        throw new RuntimeException("你好");
        // return "请求错误";
    }

    /**
     * 第二次服务降级
     * @return
     */
    public String HelloFallBackTwice(Throwable e){
        return "第二次请求错误"+e.getMessage();
    }

    /**
     * 自定义断路器
     * @return
     */
    public User getUser() {
        com.netflix.hystrix.HystrixCommand.Setter setter = com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey(""));
        // 同步执行响应方式
        User user = new UserCommand(setter,template,1L).execute();
        // 异步执行响应方式
        User user1 = new User();
        Future<User> feature = new UserCommand(setter,template,1L).queue();
        // Observable响应方式
        Observable<User> ho = new UserCommand(setter,template,1L).observe();
        Observable<User> co = new UserCommand(setter,template,1L).toObservable();
        try {
            user1 = feature.get();
            System.out.println(user1.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 自定义断路器同步响应方式 注解实现（重载实现见UserCommand）
     * @param id
     * @return
     */
    @HystrixCommand
    public User getUserById(@CacheKey("id") Long id){ // 开启缓存
        return template.getForObject("http://user-cn.eartech.helloserviceapi.service/user/{1}",User.class,id);
    }

    @CacheRemove(commandKey = "getUserById") // 清除缓存
    @HystrixCommand
    public User updateUser(@CacheKey("id") Long id,User user){
        return template.postForObject("http://user-cn.eartech.helloserviceapi.service/users",user,User.class);
    }
    /**
     * 自定义断路器异步响应方式 注解实现（重载实现见UserCommand）
     * @param id
     * @return
     */
    @HystrixCommand
    public Future<User> getUserByIdAsync(Long id){
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return template.getForObject("http://user-cn.eartech.helloserviceapi.service/user/{1}",User.class,id);
            }
        };
    }

    /**
     * 自定义断路器Observable响应方式 注解实现（UserObservableCommand）
     * ObservableExecutionMode.EAGER 表示用observe()执行方式
     * ObservableExecutionMode.LAZY 表示用toObservable()执行方式
     * @param id
     * @return
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
    public Observable<User> getObservableUserById(Long id){
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        User user1 = template.getForObject("http://user-cn.eartech.helloserviceapi.service/user/{1}",User.class,id);
                        subscriber.onNext(user1);
                        subscriber.onCompleted();
                    }
                } catch (RestClientException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }



}
