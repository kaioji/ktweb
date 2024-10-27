package com.example.demo.common.util

import com.example.demo.common.annotion.UserAuthenticate
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

//@Slf4j
@Component
class UserPermissionInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        println(">>> UserPermissionInterceptor preHandle -- ")
        if (handler is HandlerMethod) {
            // 获取用户权限校验注解(优先获取方法，无则再从类获取)
            var userAuthenticate = handler.method.annotations.firstOrNull { it is UserAuthenticate }
            if (userAuthenticate == null) {
                userAuthenticate = handler.method.declaringClass.getAnnotation(UserAuthenticate::class.java)
            }
            if (userAuthenticate == null) {
                return false
            }
            val permission = userAuthenticate.javaClass.getMethod("permission").invoke(userAuthenticate) as Boolean
            println("permission = $permission")
            return permission
        }
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        println(">>> UserPermissionInterceptor postHandle -- ")
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        println(">>> UserPermissionInterceptor afterCompletion -- ")
    }

}