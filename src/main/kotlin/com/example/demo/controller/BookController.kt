package com.example.demo.controller

import com.example.demo.book.model.*
import com.example.demo.book.service.BookServiceImp
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("book")
class BookController(
    private val bookService: BookServiceImp
) {

    @GetMapping("/get/{id}")
    fun getUser(@PathVariable id: Int): Map<String, Any> {
        return bookService.get(id)
    }

}

//PathVariable注解：获取url中的路径参数，路径参数可以有多个，PathVariable("参数名")与其对应
// gson使用：https://juejin.cn/post/6844903765603336206#heading-9