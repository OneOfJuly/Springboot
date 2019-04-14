package com.gsq.springboot.mapper;

import com.gsq.springboot.model.User;

import java.util.List;

/**
 * @program: springboot_project
 * @Date: 2019/4/7 21:20
 * @Author: Mr.GUO
 * @Description:
 */
public interface UserMapper {

    //查询所有用户
    List<User> getAllUsers(User user);

    //删除用户
    int deleteUser(Integer uid);

    User login(User user);
}
