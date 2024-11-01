package com.example.demo.common.util

import com.example.demo.user.model.TestRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody

import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer


/*
* 方法参数解析器使用
* */
@Component
class TestResolver : HandlerMethodArgumentResolver {
    // 定义哪些 控制器参数！！ 可以通过该过滤器
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        println(">>> supportsParameter -- begin...${parameter}")
        val name = parameter.parameterName
        if (name.equals("test")) {
            return true
        }
        return false
    }

    // 定义通过该过滤器的 控制器参数 的映射处理方式
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        println(">>> resolveArgument -- begin...");
        return TestRequest("test")
    }
}