package com.aimable.rest_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customConfig() {
        return new OpenAPI().info( new Info()
                .title("Java REST API class work")
                .version("1.0")
                .description("Java REST API class work")
                .contact(new Contact().email("aimable@gmail.com").url("https://github.com/aimable01"))
        );
    }
}
