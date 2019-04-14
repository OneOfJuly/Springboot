package com.gsq.springboot.interceptor;

import com.gsq.springboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: springboot_project
 * @Date: 2019/4/14 12:18
 * @Author: Mr.GUO
 * @Description:
 */
@Component
public class LoginInterceptor  implements HandlerInterceptor {
    Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String basePath = request.getContextPath();
        String path = request.getRequestURI();
        log.info("basePath:" + basePath);
        log.info("path:" + path);
        if (!doLoginInterceptor(path, basePath)) {//是否进行登陆拦截
            return true;
        }else{
            //如果登录了，会把用户信息存进session
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                log.info("用户信息为空返回登录界面" +request.getRequestURI()+"login/");
                response.sendRedirect("/login/");
                return false;
            }else{
                log.info("用户已登录,userName:" + user.getUsername());
                return true;
            }
        }
    }

    /**
     * 是否进行登陆过滤
     *
     * @param path
     * @param basePath
     * @return
     */
    private boolean doLoginInterceptor(String path, String basePath) {
        path = path.substring(basePath.length());
        Set<String> notLoginPaths = new HashSet<>();
        //设置不进行登录拦截的路径：登录注册和验证码
        notLoginPaths.add("/");
        notLoginPaths.add("/index");
        notLoginPaths.add("/login/");
        notLoginPaths.add("/register");
        notLoginPaths.add("/login/user_doLogIn.action");
        if (notLoginPaths.contains(path)) {
            return false;
        } else {
            return true;

        }
    }

}
