package com.example.demo.controller

import com.example.demo.common.annotion.UserAuthenticate
import com.example.demo.user.dao.entity.UserEntity
import com.example.demo.user.model.UserRequest
import com.example.demo.user.service.UserServiceImpl
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("user")
class UserController(
    private val userServiceImpl: UserServiceImpl
) {

    @GetMapping("/get/id/{id}")
    fun getUser(@PathVariable id: Long): UserEntity {
        return userServiceImpl.get(id)
    }

    @GetMapping("/get/username/{username}")
    fun getByUsername(@PathVariable username: String): UserEntity {
        println(username)
        return userServiceImpl.getByUsername(username)
    }

    @PostMapping("/save")
    fun getUser(@RequestBody data: UserRequest): UserEntity {
        return userServiceImpl.create(data)
    }

    @GetMapping("/all")
    fun getAllUsers(): List<UserEntity> {
        return userServiceImpl.findAll()
    }

    @GetMapping("/basicSalt")
    @UserAuthenticate(permission = true) // 不会作用
    fun basicSalt(@RequestBody encryptString: String) = userServiceImpl.basicSalt(encryptString)

    @GetMapping("/multiSalt")
    @UserAuthenticate(permission = true)
    fun multiSalt(@RequestBody encryptString: String) = userServiceImpl.multiSalt(encryptString)
}

//PathVariable注解：获取url中的路径参数，路径参数可以有多个，PathVariable("参数名")与其对应
// gson使用：https://juejin.cn/post/6844903765603336206#heading-9