package cn.eartech.userservice.service;

import cn.eartech.userservice.entity.User;
import cn.eartech.userservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/4 15:28
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        // 为了演示超时现象，我们在这里然线程休眠,时间随机 0~2000毫秒
//        try {
//            Thread.sleep(new Random().nextInt(2000));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return this.userMapper.selectByPrimaryKey(id);
    }
}
