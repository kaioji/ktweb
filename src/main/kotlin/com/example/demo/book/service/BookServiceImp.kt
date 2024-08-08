package com.example.demo.book.service

import com.example.demo.book.model.BookCreateRequest
import jakarta.annotation.Resource
import org.springframework.jdbc.core.ColumnMapRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.awt.print.Book


@Service
class BookServiceImp{
    @Resource(name = "namedParameterJdbcTemplate") //必须指定name
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate

    fun get(id:Int):Map<String,Any?>{
        var querySQL = StringBuilder("select * from Book where id = :id")
        var parameters = MapSqlParameterSource()

        parameters.addValue("id", id)
        val ret = jdbcTemplate.queryForMap(querySQL.toString(),parameters)
        println("jdbcTemplate查询结果：${ret}")

        return ret
    }

    fun create(book: BookCreateRequest):Map<String,Any?>{
        val sql = "insert into book(title,author,name) values(:title,:author,:name)"
        var parameters = MapSqlParameterSource()
        parameters.addValue("title", book.title)
        parameters.addValue("author", book.author)
        parameters.addValue("name", book.name)

        val ret = jdbcTemplate.update(sql,parameters)
    }

    fun all():List<Map<String, Any?>>{
        var querySQL = StringBuilder("select * from Book")
        var parameters = MapSqlParameterSource()

//        parameters.addValue("id", id)
        val ret = jdbcTemplate.query(querySQL.toString(),parameters,ColumnMapRowMapper())
        println("jdbcTemplate查询结果：${ret}")
        return ret
    }
}