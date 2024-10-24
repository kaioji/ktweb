package com.example.demo.common.util

data class ValidationErrorResponse(
    val message: String,
    val code: Int,
    val error:List<String>
)
