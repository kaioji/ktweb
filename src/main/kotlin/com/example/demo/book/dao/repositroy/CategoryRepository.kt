package com.example.demo.book.dao.repositroy

import com.example.demo.book.dao.entity.BookEntity
import com.example.demo.book.dao.entity.CategoryEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor


interface CategoryRepository : JpaRepository<CategoryEntity, Int>,
    JpaSpecificationExecutor<CategoryEntity> {

    fun findByName(name:String):CategoryEntity
}