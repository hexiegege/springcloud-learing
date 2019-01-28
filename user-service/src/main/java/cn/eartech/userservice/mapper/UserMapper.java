package cn.eartech.userservice.mapper;

import cn.eartech.userservice.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/4 15:30
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
}
