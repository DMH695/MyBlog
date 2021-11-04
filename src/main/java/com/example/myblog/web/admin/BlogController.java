package com.example.myblog.web.admin;

import com.example.myblog.po.Blog;
import com.example.myblog.po.User;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.TagService;
import com.example.myblog.service.TypeService;
import com.example.myblog.vo.BlogQuery;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2,sort = {"updateTime"}) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        model.addAttribute("types",typeService.listType());
        return "admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2,sort = {"updateTime"}) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        //表示blogs页面下的一个片段：blogList（前提是定义一个这样的片段）
        return "admin/blogs :: blogList";
    }
    //跳转到新增页面
    @GetMapping("/blogs/input")
    public String input(Model model){
       //初始化type和tag
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog",new Blog());

        return "admin/blogs_input";
    }
    @GetMapping("/blogs/{id}/input")
    public String editinput(@PathVariable Long id, Model model){
        //初始化type和tag
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/blogs_input";
    }
    //博客的新增和修改可以用同一个方法
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes redirectAttributes,HttpSession session){
        //拿到当前对象的user
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService. listTag(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);
        //进行非空验证
        if(b == null){
            //没保存成功时，在分类列表中给出提示信息————》还要在前端写message组件
            redirectAttributes.addAttribute("message","操作失败");
        }else {
            redirectAttributes.addAttribute("message","操作成功");
        }
        return "/admin/blogs";
    }
}
