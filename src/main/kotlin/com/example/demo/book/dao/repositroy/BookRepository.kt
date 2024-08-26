package com.example.demo.book.dao.repositroy

import com.example.demo.book.dao.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor


interface BookRepository : JpaRepository<BookEntity, Long>,
    JpaSpecificationExecutor<BookEntity> {
}