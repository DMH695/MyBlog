package com.example.myblog.service;

import com.example.myblog.po.Type;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//这是一个分类页面分页的接口
public interface TypeService {
    //新增
    Type saveType(Type type);
    //查询(根据id查询对象)
    Type getType(Long id);
    //分页查询
    Page<Type> listType(Pageable pageable);
    //修改
    Type updateType(Long id,Type type) throws NotFoundException;
    //删除
    void deleteType(Long id);
    //根据分类名称拿到分类
    Type getTypeByName(String name);

    List<Type> listType();
    //首页页面的展示（按照个数展示分类，并且还要根据数量进行排序）
    List<Type> listType(Integer size);
}
