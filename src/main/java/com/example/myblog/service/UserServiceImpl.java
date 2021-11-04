package com.example.myblog.service;

import com.example.myblog.dao.UserRepository;
import com.example.myblog.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    //根据用户名和密码在数据库中查询对应的用户
    @Override
    public User checkUser(String username, String password) {
        User user =userRepository.findByUsernameAndPassword(username, password);//调用repository的方法可以直接在数据库中查找数据
        return user;
    }
}
