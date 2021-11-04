package com.example.myblog.dao;

import com.example.myblog.po.Blog;
import com.example.myblog.po.Tag;
import com.example.myblog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
        //有时不需要写方法，因为可以直接使用继承来的方法
        Tag findByName(String name);
        @Query("select t from Tag t")
        List<Tag> findTop(Pageable pageable);
}
