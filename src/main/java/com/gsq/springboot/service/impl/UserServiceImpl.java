package com.gsq.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gsq.springboot.mapper.UserMapper;
import com.gsq.springboot.model.User;
import com.gsq.springboot.service.UserService;
import com.gsq.springboot.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot_project
 * @Date: 2019/4/7 21:28
 * @Author: Mr.GUO
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map getAllUsers(int pageNum, int pageSize,User user) {
        Map reMap = new HashMap();
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.getAllUsers(user);
        PageInfo page = new PageInfo(users,pageSize);//将查询到的数据储存到pageinfo中
        reMap.put("total",page.getTotal());
        reMap.put("rows",users);
        return reMap;
    }

    @Override
    public int deleteUser(Integer uid) {

        return userMapper.deleteUser(uid);
    }

    @Override
    public Map login(Map<String, Object> params, HttpServletRequest request) {
        User user = MapUtils.mapToBean(params,User.class);
        String username = user.getUsername();
        String password = user.getPassword();
        User logUser = userMapper.login(user);
        Map map = new HashMap<String,String>();

        if(null == logUser){
            map.put("flag","false");
            map.put("msg","用户不存在");
        }else if(logUser.getUsername().equals(username) && logUser.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("user",logUser);
            map.put("flag","true");
            map.put("BackUrl","/index/");
            map.put("msg","登录成功");
        }else if(logUser.getUsername() != username && logUser.getPassword() != password){
            map.put("flag","false");
            map.put("msg","登录失败");

        }
        return map;
    }
}
