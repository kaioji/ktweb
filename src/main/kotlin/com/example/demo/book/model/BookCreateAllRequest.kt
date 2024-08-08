package com.example.demo.book.model

import java.io.Serializable

val CONTENT = mapOf(
    "a" to "张三",
    "b" to "李四",
    "c" to "王五"
)

data class BookCreateAllRequest(
    var title: String,
    var name: String,
    var author: List<String>,
) : Serializable {
    fun validate() {
        val list = mutableListOf<String>()
        this.author.forEach { author ->
            val value = CONTENT.filter {
                it.key == author
            }.values.firstOrNull()

            value?.let {
                list.add(value)
            }
        }
        this.author = list
        println("author : ${this.author}")
    }
}
