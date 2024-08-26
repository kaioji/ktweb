package com.example.demo.book.dao.entity

import com.example.demo.common.util.ExecutionStatusEnum
import java.io.Serializable
import java.time.LocalDateTime


data class ExecutionEntity(

    var id: Long? = null,


    var createDate: LocalDateTime? = null,


    var modifiedDate: LocalDateTime? = null,


    var taskId: String = "",


    var taskName: String = "",


    var traceId: String = "",


    var parameters: String = "",


    var expectedExecutionTime: LocalDateTime = LocalDateTime.now(),


    var executionTime: LocalDateTime? = null,


    var executionDuration: Long? = null,


    var executionResult: String = "",


    var status: ExecutionStatusEnum = ExecutionStatusEnum.WAITING,


    var errorDetail: String? = null,


    var serverIp: String? = null,


    var creator: String? = null,
) : Serializable