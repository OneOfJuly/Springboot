package com.gsq.springboot.controller;

import com.gsq.springboot.model.User;
import com.gsq.springboot.service.UserService;
import com.gsq.springboot.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: springboot_project
 * @Date: 2019/4/7 21:36
 * @Author: Mr.GUO
 * @Description:
 */
@Controller
@RequestMapping("index")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String turnIndex(){

        return "index";
    }
    @RequestMapping("/user")
    public String turnUser(){

        return "user";
    }
    @ResponseBody
    @RequestMapping("/userList")
    public Map queryAll(int page, int rows,@RequestParam Map<String, Object> params){
        User user = MapUtils.mapToBean(params,User.class);
        System.out.println(user);
        Map  map =  userService.getAllUsers(page,rows,user);
        return  map;

    }
}
