package com.aimable.NestFit.config;

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
                .title("Nest Fit API")
                .version("1.0")
                .description("REST API for Nest Fit, a social inclusion community")
                .contact(new Contact().email("aimable.kwizera14@gmail.com").url("https://github.com/aimable01"))
        );
    }
}
