package com.multi.restapi.section05.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi swaggerApi() {
        return GroupedOpenApi.builder()
                .group("Spring Boot Swagger 연동 테스트")
                .packagesToScan("com.multi.restapi.section05.swagger")
                .build();
    }
}
