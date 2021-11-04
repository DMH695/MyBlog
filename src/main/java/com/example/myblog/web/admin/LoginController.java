package com.example.myblog.web.admin;

import com.example.myblog.po.User;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")//设置全局路径
public class LoginController {
    @Autowired
    private UserService userService;
    //输入/admin就自动会跳转到login页面
    @GetMapping
    public String loginPage(){
        return"admin/login";
    }


    //点击登录时是以post的形式提交的
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:admin";  //必须要重定向，否则路径会有问题
        }
    }
    //注销
    @GetMapping("/logout")
    public  String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }



}