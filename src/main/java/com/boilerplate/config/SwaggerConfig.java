package com.boilerplate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(title = "지만이의 과제 명세서",
        description = "게시판과 정산",
        version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedOpenApi(){
        return GroupedOpenApi.builder()
            .group("v1")
            .pathsToMatch("/api/**")
            .build();
    }

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
            .info(info());
    }

    @Bean
    public Info info(){
        return new Info()
            .title("title")
            .description("desc");
    }
}
