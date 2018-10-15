package com.chenfeng.girl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.concurrent.ExecutionException;

@Aspect
@Component
public class HttpAspect {

    private final static Logger log = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.chenfeng.girl.controller.GirlController.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =  attributes.getRequest();
        log.info("local_ip={},remote_ip={},url={},method={},class_method={},args={}",
                request.getLocalAddr(),request.getRemoteAddr(),request.getRequestURL(),
                request.getMethod(),joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName(),                      joinPoint.getArgs());
        log.info("浏览器信息："+request.getHeader("User-Agent"));

    }

    @After("log()")
    public void doAfter(){
        log.info("我是方法执行之后调用的操作");
    }
}
