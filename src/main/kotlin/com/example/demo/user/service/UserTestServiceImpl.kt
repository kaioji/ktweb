package com.example.demo.user.service

import com.example.demo.common.util.ActiveProfiles
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile(ActiveProfiles.DEV)  //还可以在配置类类上、配置类方法上使用
class UserTestServiceImpl {
    fun test() {
        println("user module test")
    }
}