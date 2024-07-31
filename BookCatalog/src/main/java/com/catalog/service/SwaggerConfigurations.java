package com.catalog.service;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigurations {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
            .info(new Info()
                    .title("CATALOG-SERVICE")
                    .version("0.0.1-snapshot")
                    .description("Documentation on Catalog Service"));
    }
}
