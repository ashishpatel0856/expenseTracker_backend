package com.ashish.MoneyManager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI moneyManagerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(" Money Manager API Documentation")
                        .version("1.0")
                        .description("Comprehensive REST API documentation for the Money Manager backend."));
    }
}
