package com.gsq.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @program: springboot_project
 * @Date: 2019/4/7 21:04
 * @Author: Mr.GUO
 * @Description:
 */
public class User {
    private int uid;
    private  String username;
    private  String password;
    private  String address;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private  Date   birthday;
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
