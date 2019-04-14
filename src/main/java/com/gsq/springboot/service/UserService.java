package com.gsq.springboot.service;

import com.gsq.springboot.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: springboot_project
 * @Date: 2019/4/7 21:27
 * @Author: Mr.GUO
 * @Description:
 */
public interface UserService {

    //查询所有用户
    Map getAllUsers(int pageNum, int pageSize, User user);

    //删除用户
    int deleteUser(Integer uid);

    Map login(Map<String,Object> params, HttpServletRequest request);

}
