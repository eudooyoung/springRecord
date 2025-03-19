package com.multi.shop.common;

import com.multi.shop.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/board/**") // **: 내부(하위) 경로에 있는 파일 까지 * 적용
                .excludePathPatterns("/login", "/signup", "/css/**", "/js/**", "/images/**", "/static/**", "/resources/**"); // 정적 리소스 및 로그인/회원가입 페이지 제외
    }
}
