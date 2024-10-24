package com.example.demo.book.service

import com.example.demo.book.dao.entity.BookEntity
import com.example.demo.book.dao.repositroy.BookRepository
import com.example.demo.book.model.BookCreateAllRequest
import com.example.demo.book.model.BookCreateRequest
import jakarta.annotation.Resource
import org.modelmapper.ModelMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import kotlin.reflect.full.memberProperties


@Service
class BookServiceImp(
    var bookRepository: BookRepository
) {
    @Resource(name = "namedParameterJdbcTemplate") //必须指定name
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate
    private var modelMapper: ModelMapper = ModelMapper()

    fun get(id: Int): Map<String, Any> {
        var querySQL = StringBuilder("select * from Book where id = $id")
        var parameters = MapSqlParameterSource()
        parameters.addValue("id", id)
        val ret = jdbcTemplate.queryForMap(querySQL.toString(), parameters)
        println("get result: $ret")
        val mapper = modelMapper.map(ret, BookEntity::class.java)
        println("mapper obj: $mapper")

        return ret
    }

    fun getRetEntity(id: Int): BookEntity{
        var querySQL = StringBuilder("select * from Book where id = $id")
        var parameters = MapSqlParameterSource()
        parameters.addValue("id", id)
        val ret = jdbcTemplate.queryForMap(querySQL.toString(), parameters)
        println("get result: $ret")
        val mapper = modelMapper.map(ret, BookEntity::class.java)
        println("mapper obj: $mapper")

        return mapper
    }

    fun all(): List<BookEntity> {
        var querySQL = "select * from Book"
        var parameters = MapSqlParameterSource()

        return jdbcTemplate.queryForList(querySQL, parameters, BookEntity::class.java)
    }

    fun create(request: BookCreateRequest): BookEntity{
        val ans = modelMapper.map(request, BookEntity::class.java)
        println("create mapper obj: $ans")
        val properties = ans.javaClass.kotlin.memberProperties
        for (property in properties){
            // 获取属性名
            val propName = property.name

            // 获取属性值
            val propValue = property.get(ans)
            println("$propName = $propValue")
        }

//        val sql = "insert into book(title,author,name) values(?,?,?)"
//        jdbcTemplate.update(sql, request.title,request.author,request.name)
        val entity = BookEntity()
//        entity.name = request.name
        entity.title = request.title
        entity.author = request.author
        bookRepository.save(entity)
        return entity
    }

    fun createAll(request: BookCreateAllRequest): BookEntity{
        request.validate()
        val ans = modelMapper.map(request, BookEntity::class.java)
        println("create mapper obj: $ans")
        val properties = ans.javaClass.kotlin.memberProperties


        val entity = BookEntity()
        entity.name = request.name
        entity.title = request.title
        entity.author = request.author[0]
        bookRepository.save(entity)
        return entity
    }
}