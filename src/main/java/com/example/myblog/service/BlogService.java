package com.example.myblog.service;

import com.example.myblog.po.Blog;
import com.example.myblog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog getBlog(Long id);
    //分页
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
    //前端展示的分页
    Page<Blog> listBlog(Pageable pageable);
    Blog saveBlog(Blog blog);
    Blog updateBlog(Long id,Blog blog);
    void deleteBlog(Long id);
    //前端展示的博客推荐
    List<Blog> listRecommendBlogTop(Integer size);
}
