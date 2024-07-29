package com.example.demo.book.dao.entity

import jakarta.persistence.*

@Entity
@Table(name = "book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int? = null,

    var title:String,

    var author:String,

    var name:String

)
