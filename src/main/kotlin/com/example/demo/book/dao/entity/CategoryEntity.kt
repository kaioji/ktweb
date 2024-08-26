package com.example.demo.book.dao.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener::class)
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @CreatedDate
    @Column(name = "gmt_create")
    var createDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "gmt_modified", nullable = true)
    var modifiedDate: LocalDateTime? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "title")
    var order: Int= 0
)