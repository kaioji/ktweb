package com.example.demo.book.model

import java.io.Serializable

data class BookCreateRequest(
    var title:String,
    var authorName:String,
): Serializable
