package com.adailton.currencyconverter.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Currency Converter API")
                    .version("1.0")
                    .description("API para conversão de moedas e gerenciamento de usuários.")
            )
    }
}