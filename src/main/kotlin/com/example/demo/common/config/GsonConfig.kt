package com.example.demo.common.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.context.annotation.Bean

class GsonConfig {
    private val pattern = ""

    @Bean
    fun gson(): Gson {
        return GsonBuilder().create()
    }
}