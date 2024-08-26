package com.example.demo.book.model

import java.time.LocalDateTime


data class BookQueryRequest(
    var id: Int? = null,
    var categoryName: String? = null,
    var title: String? = null,
    var author: String? = null,
    var likeNum: Int? = null,
    var viewNum: Int? = null,
    var createDate: LocalDateTime? = null,
    var page: Int = 0,
    var size: Int = 10
)
