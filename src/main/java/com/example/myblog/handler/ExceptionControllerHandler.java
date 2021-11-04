package com.example.myblog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
//拦截所有带@Controller页面的错误信息
@ControllerAdvice
public class ExceptionControllerHandler {
    //表示将获取页面的异常
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHander(HttpServletRequest request,Exception e){
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        //目录名加文件名,拦截到错误后，会跳转到error页面中
        mv.setViewName("error/error");
        return mv;
    }
}
