package com.example.demo.common.config

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component


// spring-aop使用
//@Order(1)
//@Aspect
//@Component
class DemoAspect {

    @Pointcut(value = "execution( * com.example.demo.controller.BookController.*(..))")
    fun pointcut() {
    }

    @Before("pointcut()")
    fun before(joinPoint: JoinPoint) {
        println("[DemoAspect] before advice")
    }

    @Around("pointcut()")
    fun around(proceedingJoinPoint: ProceedingJoinPoint) {
        println("[DemoAspect] around advice enter")
        proceedingJoinPoint.proceed()
        println("[DemoAspect] around advice out")
    }

    @After("pointcut()")
    fun after(joinPoint: JoinPoint) {
        println("[DemoAspect] after advice")
    }

    @AfterReturning(value = "pointcut()")
    fun afterReturning(joinPoint: JoinPoint) {
        println("[DemoAspect] afterReturning advice")
    }


    @AfterThrowing(value = "pointcut()")
    fun afterThrowing(joinPoint: JoinPoint, error: Throwable) {
        println("[DemoAspect] afterThrowing advice")
    }
}