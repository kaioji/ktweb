package com.example.demo.book.model

import java.io.Serializable
import javax.validation.constraints.NotBlank

data class BookCreateRequest(
    @NotBlank
    var title: String,

    @NotBlank
    var author: String,

    var name: String
) : Serializable
