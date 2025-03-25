package com.multi.restproduct.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer { //WebMvcConfigurer를 구현하여 정적 리소스 경로를 설정하는 클래스


    @Value("${image.add-resource-locations}")
    private String ADD_RESOURCE_LOCATION;

    @Value("${image.add-resource-handler}")
    private String ADD_RESOURCE_HANDLER;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(ADD_RESOURCE_HANDLER) //    // 요청 URL 패턴을 지정 (예: /productimgs/**)
                .addResourceLocations("file://" +ADD_RESOURCE_LOCATION); //    // 실제 파일 시스템의 경로를 지정 (예: file:///productimgs/)
//
//
//        add-resource-locations에 file://를 붙이기 때문에,
//        add-resource-locations: /productimgs/ 는 절대 경로로 존재해야 함.
//
//        즉, 실제 디렉토리 /productimgs/가 운영체제의 루트 경로에 생성되어 있어야 정상 동작
//
//                예: 윈도우에서는 C:/productimgs/
//                예: 리눅스에서는 /productimgs/

    }

}
