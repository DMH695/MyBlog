package com.example.myblog.dao;

import com.example.myblog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
//Long为主键的类型
//JpaRepository里面的方法是对数据库的操作（直接使用方法就可以管理数据库）
public interface UserRepository extends JpaRepository<User,Long> {
    //定义方法只需要遵循jpa的命名规则
    //这个方法可以直接通过用户名和密码去查看数据库
    User findByUsernameAndPassword(String username,String password);
}
