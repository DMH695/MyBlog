package com.example.myblog.dao;

import com.example.myblog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//Long为主键（Long类型的id）的类型

public interface TypeRepository extends JpaRepository<Type,Long> {
    //有时不需要写方法，因为可以直接使用继承来的方法
    Type findByName(String name);
    //自定义查询
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
