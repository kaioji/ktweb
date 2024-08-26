package com.example.demo.common.config

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Order(1)
@Aspect
@Component
class TransactionalAspect {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    fun pointcut() {
    }

    @Before("pointcut()")
    fun beforeTransactionalMethod(joinPoint: JoinPoint) {
        println("beforeTransactionalMethod")
        val methodName = joinPoint.getSignature().toShortString();
        logger.info("Transaction started on method: {}", methodName);
    }

    @After("pointcut()")
    fun afterTransactionalMethod(joinPoint: JoinPoint) {
        println("afterTransactionalMethod")
        val methodName = joinPoint.getSignature().toShortString();
        logger.info("Transaction ended on method: {}", methodName);
    }
}
