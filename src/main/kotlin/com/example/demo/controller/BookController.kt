package com.example.demo.controller

import com.example.demo.book.dao.entity.BookEntity
import com.example.demo.book.model.*
import com.example.demo.book.service.BookServiceImp
import org.springframework.boot.context.properties.bind.BindResult
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("book")
class BookController(
    private val bookService: BookServiceImp
) {

    @GetMapping("/get/{id}")
    fun getUser(@PathVariable id: Int): Map<String, Any> {
        return bookService.get(id)
    }

    @GetMapping("/get_by_id/{id}")
    fun getUserByID(@PathVariable id: Int): BookEntity {
        return bookService.getRetEntity(id)
    }

    @PostMapping("/create")
    fun create(@Valid @RequestBody request: BookCreateRequest,bindRequest: BindingResult): BookEntity {
        return bookService.create(request)
    }

    @PostMapping("/createAll")
    fun createAll(@RequestBody request: BookCreateAllRequest): BookEntity {
        return bookService.createAll(request)
    }

}

//PathVariable注解：获取url中的路径参数，路径参数可以有多个，PathVariable("参数名")与其对应
// gson使用：https://juejin.cn/post/6844903765603336206#heading-9