package com.example.demo.book.dao.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener::class)
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @CreatedDate
    @Column(name = "gmt_create")
    var createDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "gmt_modified", nullable = true)
    var modifiedDate: LocalDateTime? = null,

    @Column(name = "category_id")
    var categoryId: String? = null,

    @Column(name = "title")
    var title: String? = null,

    @Column(name = "author")
    var author: String? = null,

    @Column(name = "content")
    var content: String? = null,

    @Column(name = "keywords")
    var keywords: String = "",

    @Column(name = "view_num")
    var viewNum: Long? = null,

    @Column(name = "like_num")
    var likeNum: Long? = null,
    )
