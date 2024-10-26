package com.example.demo.common.config

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/*
* jasypt使用内置的基类加密器/子类加密器时需要配置对应基类加密器对应的Bean
* 内置基类加密器：StringEncryptor、BigDecimalEncryptor、ByteEncryptor、BigIntegerEncryptor
* */
@Configuration
class JasyptConfig {
    @Value("\${jasypt.encryptor.password}")
    private val password: String = ""

    @Bean("jasyptStringEncryptor")  //StringEncryptor配置
    fun stringEncryptor(): StringEncryptor {
        println("使用内置加密器")
        val encryptor = StandardPBEStringEncryptor()
        encryptor.setPassword(password)
        return encryptor
    }
}
