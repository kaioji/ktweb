package com.example.demo.common.config

import com.example.demo.common.util.UserPermissionInterceptor
import jakarta.annotation.Resource
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    @Resource
    private lateinit var userPermissionInterceptor: UserPermissionInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(userPermissionInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/user/basicSalt")
            .order(0)
    }
}