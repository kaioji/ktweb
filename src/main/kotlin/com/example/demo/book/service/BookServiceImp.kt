package com.example.demo.book.service

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

    fun get(id:Int):Map<String, Any>{
        var querySQL = StringBuilder("select * from Book where id = $id")
        var parameters = MapSqlParameterSource()
        parameters.addValue("id", id)

        val ret = jdbcTemplate.query(querySQL.toString(),parameters,ColumnMapRowMapper())
    }

    fun all():List<Book>{
        var querySQL = "select * from Book"
        var parameters = MapSqlParameterSource()

        return jdbcTemplate.queryForList(querySQL,parameters,Book::class.java)


    }
}