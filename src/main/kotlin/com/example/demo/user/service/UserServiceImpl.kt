package com.example.demo.user.service

import com.example.demo.user.dao.entity.UserEntity
import com.example.demo.user.dao.repository.UserRepository
import com.example.demo.user.model.UserRequest
import com.google.gson.Gson
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory
import org.slf4j.Logger


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val modelMapper: ModelMapper,
    private val gson: Gson,
){
    // 获取Logger对象(使用slf4j)
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun findAll(): List<UserEntity> {
        val list = userRepository.findAll()
//        slf4j 日志存储在哪？
        logger.info("Retrieving all users: {}",gson.toJson(list))
        println(gson.toJson(list))
        return list
    }

    fun get(userId:Long): UserEntity {
        return userRepository.getReferenceById(userId)
    }

    fun getByUsername(username:String): UserEntity {
        return userRepository.findByUsername(username)
    }

    fun create(request: UserRequest):UserEntity {
//        var _entity = UserEntity(username = request.username,password = request.password,email = request.email,gender = request.gender)
//        ClassObject::class.java???
        val entity = modelMapper.map(request,UserEntity::class.java)
        println(entity)

        userRepository.saveAndFlush(entity)
        return entity
    }

    fun UserServiceImpl.toUser(): UserEntity? {
        return null
    }
}