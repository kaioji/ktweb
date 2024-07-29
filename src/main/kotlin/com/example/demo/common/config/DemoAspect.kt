package com.example.demo.common.config

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.springframework.context.annotation.Configuration


// spring-aop使用
@Aspect
@Configuration
class DemoAspect {

    @AfterThrowing(pointcut = "execution( * com.example.demo.controller.*.*(..))")
    fun afterThrowing(joinPoint: JoinPoint,error: Throwable) {

    }
}