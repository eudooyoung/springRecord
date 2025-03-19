package com.multi.dianno.e_locale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ContextConfiguration {

//    @Bean
//    public ReloadableResourceBundleMessageSource reloadMessage() {
//
////        접속하는(즉 요청하는) 세션의 로케일정보에 따라 properties를 자동 재로딩하는 용도의 MessageSource 구현체
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//
//        messageSource.setBasename("message");
//
//        messageSource.setCacheSeconds(10);
//
//        messageSource.setDefaultEncoding("UTF-8");
//
//        return messageSource;
//
//    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {


        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();


        messageSource.setBasename("message");
        messageSource.setCacheSeconds(10);
        messageSource.setDefaultEncoding("UTF-8");


        return messageSource;
    }
}
