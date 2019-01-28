package cn.eartech.ribbonconsumer.controller;

import cn.eartech.ribbonconsumer.entity.User;
import cn.eartech.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;


/**
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/8 16:26
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HelloService service;

    @GetMapping("/ribbon-consumer")
    public String helloConsumer(){
       return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    @GetMapping("/hystrix")
    public String helloHystrix(){
        return service.HelloService();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id")Long id){
        return restTemplate.getForObject("http://user-cn.eartech.helloserviceapi.service/user/{1}",User.class,id);
    }
    @GetMapping("/hystrix/user/{id}")
    public User getUser(@PathVariable("id")Long id) {
        try {
            final User[] user = {new User()};
            //user[0] = cn.eartech.helloserviceapi.service.getUser();
            //user[0] = cn.eartech.helloserviceapi.service.getUserByIdAsync(id).get();
            service.getObservableUserById(id).subscribe((User res) ->{
                user[0] = res;
                System.out.println(res.getName());
            });
            return user[0];
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        // RxJava
        // 创建事件源Observable
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("Hello RxJava");
                subscriber.onNext("I am a programmer");
                subscriber.onCompleted();
            }
        });

        // 创建订阅者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Observable:"+s);
            }
        };
        observable.subscribe(subscriber);
    }
}
