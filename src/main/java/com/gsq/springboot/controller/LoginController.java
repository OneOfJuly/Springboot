package com.gsq.springboot.controller;

import com.gsq.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: springboot_project
 * @Date: 2019/4/14 13:04
 * @Author: Mr.GUO
 * @Description:
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String turnIndex(){

        return "login";
    }

    @ResponseBody
    @RequestMapping("/user_doLogIn.action")
    public Map userLogin(@RequestParam Map<String, Object> params, HttpServletRequest request){
        Map  map =  userService.login(params,request);
        return  map;
    }
    @RequestMapping("/loginout.action")
    public String userLoginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        System.out.println("======"+session);
        return  "login";
    }
}
