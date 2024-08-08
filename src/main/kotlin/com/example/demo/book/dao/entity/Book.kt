package com.example.demo.book.dao.entity

import jakarta.persistence.*

@Entity
@Table(name = "book")
class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "title")
    var title: String?=null,

    @Column(name = "author")
    var author: String?=null,

    @Column(name = "name")
    var name: String?=null

)
