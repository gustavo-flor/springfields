package com.github.gustavoflor.springfields.entrypoint.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String INFO_TITLE = "Springfields";

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(info());
    }

    private Info info() {
        return new Info().title(INFO_TITLE);
    }

}
