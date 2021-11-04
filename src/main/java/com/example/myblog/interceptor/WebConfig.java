package com.example.myblog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//配置类,继承Web适配器
//配置需要对哪些路径进行拦截，而拦截器中写的是拦截逻辑（说明情况下需要拦截）
@Configuration//只有写上这个注解，springboot才能认为这是一个配置类
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加写好的拦截器,并说明对哪些路径进行拦截，并且需要排除一些路径,login需要排除否则无法进行表单提交
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin").excludePathPatterns("/admin/login");

    }
}
