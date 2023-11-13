package com.microfinance.agroInvest.Swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration

public class SwaggerConfig {

        @Bean
        public OpenAPI usersMicroserviceOpenAPI() {
            return new OpenAPI()
                    .info(new Info().title("API AgroInvest")
                            .description("API de gestion de microCredit")
                            .version("1.0"));
        }
    }
