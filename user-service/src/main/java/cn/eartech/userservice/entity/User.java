package cn.eartech.userservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @des:
 * @author: shanfa
 * @date: 2019/1/4 15:21
 */
@Getter
@Setter
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public User() {
    }

    /** 用户名*/
    private String userName;

    /** 密码*/
    private String password;

    /** 姓名*/
    private String name;

    /** 年龄*/
    private Integer age;

    /** 性别，1男性，2女性*/
    private Integer sex;

    /** 出生日期*/
    private Date birthday;

    /** 创建时间*/
    private Date created;

    /** 更新时间*/
    private Date updated;

    /** 备注*/
    private String note;

}
