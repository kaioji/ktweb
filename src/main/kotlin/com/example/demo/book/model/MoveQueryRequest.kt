package com.example.demo.book.model

import java.io.Serializable
import java.time.LocalDateTime


/**
 * 腾挪任务执行情况
 *
 * @author kaioji@tencent.com
 * @date 2024-08-19
 */

data class MoveQueryRequest(

    var zoneId: List<Int> = emptyList(),

    var taskName: List<String>? = emptyList(),

    val executedDate: LocalDateTime? = null,

    val status: String? = null,

    val creator: String? = null,

    var taskId: String? = null,

    var traceId: String? = null,

):Serializable
