package com.example.myblog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;
     String name;
    //表示type的这一端是one
    @OneToMany(mappedBy = "type")//参数说明：type是被维护的（被维护Type和Blog之间的关系）
    //一个分类对应多个blog
    private List<Blog> blogs = new ArrayList<>();

    public Type(){};
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
