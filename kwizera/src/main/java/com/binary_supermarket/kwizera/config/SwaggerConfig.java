package com.binary_supermarket.kwizera.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info( new Info()
                .title("KALIM's Binary Supermarket API")
                .version("1.0")
                .description("Java REST API for Kalim's Binary Supermarket API. - CAT practical")
                .contact(new Contact().email("aimable@gmail.com").url("https://github.com/aimable01"))
        );
    }
}
