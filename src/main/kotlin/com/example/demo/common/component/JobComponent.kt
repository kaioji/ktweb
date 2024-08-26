package com.example.demo.common.component

import com.example.demo.book.dao.repositroy.BookRepository
import com.example.demo.user.dao.entity.UserEntity
import com.example.demo.user.dao.repository.UserRepository
import jakarta.annotation.Resource
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
class JobComponent {
    @Resource
    lateinit var userRepository: UserRepository
    @Resource
    lateinit var bookRepository: BookRepository

    private var id = 0

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun createUser() {
        val user = UserEntity(username = "test-${id}", password = "1213456", email = "<EMAIL>", gender = true)
        id++
        userRepository.saveAndFlush(user)
    }
}