package com.example.myblog.service;

import com.example.myblog.po.Tag;
import com.example.myblog.po.Type;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    //新增
    Tag saveTag(Tag tag);
    //查询(根据id查询对象)
    Tag getTag(Long id);
    //分页查询
    Page<Tag> listTag(Pageable pageable);
    //修改
    Tag updateTag(Long id,Tag tag) throws NotFoundException;
    //删除
    void deleteTag(Long id);
    //根据分类名称拿到分类
    Tag getTagByName(String name);
    //不传递任何的参数获取到所有的tag
    List<Tag> listTag();

     List<Tag> listTag(String ids);
     //首页页面的展示（只展示指定数量个数的标签）
     List<Tag> listTag(Integer size);
}
