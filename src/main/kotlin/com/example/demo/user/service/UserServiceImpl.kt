package com.example.demo.user.service

import com.example.demo.user.dao.entity.UserEntity
import com.example.demo.user.dao.repository.UserRepository
import com.example.demo.user.model.UserRequest
import com.google.gson.Gson
import jakarta.annotation.PostConstruct
import jakarta.annotation.Resource
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PBEStringEncryptor
import org.jasypt.util.text.BasicTextEncryptor
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userTestServiceImpl: UserTestServiceImpl,
    private val modelMapper: ModelMapper,
    private val gson: Gson,
) {
    // 获取Logger对象(使用slf4j)
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${jasypt.encryptor.password}")
    private val password: String = ""

    @PostConstruct
    fun init() {
        userTestServiceImpl.test()
    }

    fun findAll(): List<UserEntity> {
        val list = userRepository.findAll()
//        slf4j 日志存储在哪？
        logger.info("Retrieving all users: {}", gson.toJson(list))
        println(gson.toJson(list))
        return list
    }

    fun get(userId: Long): UserEntity {
        return userRepository.getReferenceById(userId)
    }

    fun getByUsername(username: String): UserEntity {
        return userRepository.findByUsername(username)
    }

    fun create(request: UserRequest): UserEntity {
        val entity = modelMapper.map(request, UserEntity::class.java)
        println(entity)

        userRepository.saveAndFlush(entity)
        return entity
    }

    // 基础使用
    fun basicSalt(encryptString: String): String {
        println("配置密码为：$password")
        val textEncryptor = BasicTextEncryptor()

        //加密所需的salt(盐)
        textEncryptor.setPassword(password)

        //要加密的数据（数据库的用户名或密码）
        val username = textEncryptor.encrypt("root")
        val password = textEncryptor.encrypt("root")
        println("username:$username")
        println("password:$password")
        return textEncryptor.encrypt(encryptString)
    }

    @Resource
    private lateinit var stringEncryptor: StringEncryptor

    @Resource
    private lateinit var pbeStringEncryptor: PBEStringEncryptor

    // 内置加密器使用
    fun multiSalt(encryptString: String): Map<String, String> {
        val map = hashMapOf<String, String>()
        map["stringEncryptor"] = stringEncryptor.encrypt(encryptString)
        map["pbeStringEncryptor"] = pbeStringEncryptor.encrypt(encryptString)
        return map
    }
}