package cn.eartech.ribbonconsumer.command;

import cn.eartech.ribbonconsumer.entity.User;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

/**
 * 自定义断路器Observable响应方式  重载方法实现（还可以用注解方式实现）
 * 调用方式及注解实现见HelloService
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/9 19:56
 */

public class UserObservableCommand extends HystrixObservableCommand<User> {
    private RestTemplate template;
    private Long id;

    public UserObservableCommand(Setter setter, RestTemplate template, Long id) {
        super(setter);
        this.template = template;
        this.id = id;
    }
    @Override
    protected Observable<User> construct() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {

                try {
                    if (!subscriber.isUnsubscribed()) {
                        User user = template.getForObject("http://user-cn.eartech.helloserviceapi.service/user/{1}",User.class,id);
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }
                } catch (RestClientException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
