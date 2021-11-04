package com.example.myblog.service;

import com.example.myblog.po.User;

public interface UserService {
    //查询用户名和密码的方法
    User checkUser(String username,String password);
}
