package com.example.demo.book.model

import com.example.demo.common.util.ExecutionStatusEnum
import java.time.LocalDateTime

data class ExecutionResultTemp(
    val status: ExecutionStatusEnum,
    val result: Map<String, Any>,
    val parameters: HashMap<String, Any>,
    val date: LocalDateTime,

)