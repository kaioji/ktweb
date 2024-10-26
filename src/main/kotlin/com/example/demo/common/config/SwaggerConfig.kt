package com.example.demo.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.ExternalDocumentation

@Configuration
class SwaggerConfig {
    @Bean
    fun swaggerOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("标题")
                    .contact(Contact())
                    .description("我的API文档")
                    .version("v1")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("外部文档")
                    .url("https://springshop.wiki.github.org/docs")
            )
    }
}
