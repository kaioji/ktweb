package com.example.demo.common.config

import com.example.demo.common.util.TestResolver
import com.example.demo.common.util.UserPermissionInterceptor
import jakarta.annotation.Resource
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    @Resource
    private lateinit var userPermissionInterceptor: UserPermissionInterceptor

    @Resource
    private lateinit var testResolver: TestResolver

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        //  添加参数解析器
        resolvers.add(testResolver)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        // 可以添加多个拦截器，一般只添加一个
        // addPathPatterns("/**") 表示对所有请求都拦截
        // .excludePathPatterns("/xx") 表示排除对/xx请求的拦截
        // 多个拦截器可以设置order顺序，值越小，preHandle越先执行，postHandle和afterCompletion越后执行
        // order默认的值是0，如果只添加一个拦截器，可以不显示设置order的值
        registry.addInterceptor(userPermissionInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/user/basicSalt")
            .excludePathPatterns("/user/test")
            .order(0)
    }
}