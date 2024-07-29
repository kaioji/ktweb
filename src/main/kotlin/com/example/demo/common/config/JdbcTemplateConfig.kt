package com.example.demo.common.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
class JdbcTemplateConfig {
    @Autowired//需要在Bean对象中使用
    private lateinit var dataSource:DataSource

    @Primary
    @Bean("jdbcTemplate")
    @Qualifier("jdbcTemplate")
    fun jdbcTemplate(): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }

    @Primary
    @Bean("namedParameterJdbcTemplate")
    @Qualifier("namedParameterJdbcTemplate")
    fun namedParameterJdbcTemplate(): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }
}