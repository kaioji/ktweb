package com.example.demo.book.model




data class MoveStatsDetail(

    var zoneId: Long = 0,


    var total: Int = 0,


    var waiting: Int = 0,


    var pending: Int = 0,


    var success: Int = 0,


    var failed: Int = 0,


    var deviceClasses: Any? = null
)


