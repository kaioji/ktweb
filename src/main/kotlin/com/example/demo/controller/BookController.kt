package com.example.demo.controller

import com.example.demo.book.service.BookServiceImp
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