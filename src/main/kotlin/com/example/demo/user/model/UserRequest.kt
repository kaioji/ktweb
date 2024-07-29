package com.example.demo.user.model

import java.io.Serializable

// 请求体数据:业务类对象
data class UserRequest(
    val username: String,
    val password: String,
    val email: String,
    val gender:Boolean
):Serializable
//没有实现Serializable接口也能正常运行，data class 默认实现了Serializable（chatgpt）