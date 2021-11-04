package com.example.myblog.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
//做一个切面日志，也就是在某个Controller类的方法执行的前后切入，然后打印日志
@Aspect
@Component
public class LogAspect {
    //添加日志记录器
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //表示对controller包下的所有方法（任何参数）做切面
    @Pointcut("execution(* com.example.myblog.controller.*.*(..))")
    public void log(){}
    @Before("log()")
    public void doBefore(){
        logger.info("-------------dobefore----------------");
    }
    @After("log()")
    public void doAfter(){
        logger.info("---------------doafter---------------");
    }
    //在得到返回值后执行（中间）
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("Result : {}",result);
    }
}
