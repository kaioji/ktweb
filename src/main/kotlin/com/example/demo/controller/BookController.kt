package com.example.demo.controller

import com.example.demo.book.dao.entity.BookEntity
import com.example.demo.book.model.*
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

    @GetMapping("/get_by_id/{id}")
    fun getUserByID(@PathVariable id: Int): BookEntity {
        return bookService.getRetEntity(id)
    }

    @PostMapping("/create")
    fun create(@RequestBody request: BookCreateRequest): BookEntity {
        return bookService.create(request)
    }

    @PostMapping("/createAll")
    fun createAll(@RequestBody request: BookCreateAllRequest): BookEntity {
        return bookService.createAll(request)
    }



    //    @GetMapping("/get/username/{username}")
//    fun getByUsername(@PathVariable username:String):UserEntity{
//        println(username)
//        return userServiceImpl.getByUsername(username)
//    }
//
//    @PostMapping("/save")
//    fun getUser(@RequestBody data:UserRequest):UserEntity{
//        return userServiceImpl.create(data)
//    }
//
//    @GetMapping("/all")
//    fun getAllUsers():List<UserEntity>{
//        return userServiceImpl.findAll()
//    }
}

//PathVariable注解：获取url中的路径参数，路径参数可以有多个，PathVariable("参数名")与其对应