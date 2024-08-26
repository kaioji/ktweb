package com.example.demo.controller

import com.example.demo.book.dao.entity.BookEntity
import com.example.demo.book.model.*
import com.example.demo.book.service.BookServiceImp
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    private val bookService: BookServiceImp
) {

    @GetMapping("/queryId/{id}")
    fun queryById(@PathVariable id: Long): BookEntity? {
        return bookService.getById(id)
    }

    @GetMapping("/query")
    fun query(@RequestBody request: BookQueryRequest): Page<BookEntity> {
        return bookService.query(request)
    }

    @PostMapping("/create")
    fun create(@RequestBody request: BookCreateRequest): BookEntity {
        return bookService.create(request)
    }

    @PostMapping("/modify")
    fun modify(@RequestBody request: BookCreateRequest): BookEntity {
        return bookService.modify(request)
    }


    @GetMapping("/test")
    fun test(): Map<String, Any> {
        val map = mutableMapOf("zoneId" to 1000)
        map.putAll(
            mapOf(
                "migrateInstanceCount" to map.getOrDefault("migrateInstanceCount", 0),
                "migrateInstanceCpu" to map.getOrDefault("migrateInstanceCpu", 0),
                "emptyHostCount" to map.getOrDefault("emptyHostCount", 0),
            )
        )
        return map
    }
}

//PathVariable注解：获取url中的路径参数，路径参数可以有多个，PathVariable("参数名")与其对应
