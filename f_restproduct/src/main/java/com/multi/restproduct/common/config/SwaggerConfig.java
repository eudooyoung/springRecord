package com.multi.restproduct.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
//    http://localhost:8090/swagger-ui/index.html
    @Bean
    public GroupedOpenApi swaggerApi() {
        return GroupedOpenApi.builder()
                .group("Spring Boot Swagger 연동 테스트")
                .packagesToScan("com.multi.restproduct")
                .build();
    }
}
