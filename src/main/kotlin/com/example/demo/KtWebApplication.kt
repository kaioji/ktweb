package com.example.demo

import com.ulisesbocchio.jasyptspringboot.environment.StandardEncryptableEnvironment
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

//jasypt-spring-boot-starter不需要该注解
//@EnableEncryptableProperties
@SpringBootApplication
class KtWebApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder()
        .environment(StandardEncryptableEnvironment())  //必须设置，否则起不来
        .sources(KtWebApplication::class.java)
        .run(*args)
}