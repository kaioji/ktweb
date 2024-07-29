package com.example.demo.user.dao.repository

import com.example.demo.user.dao.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Component
import java.util.*


//定义entity 的数据库操作
// 简单的CRUD 只需要继承JpaRepository<实体类，主键类型>
interface UserRepository : JpaRepository<UserEntity, Long>,
    JpaSpecificationExecutor<UserEntity> {
    fun findByUsername(username: String): UserEntity
}