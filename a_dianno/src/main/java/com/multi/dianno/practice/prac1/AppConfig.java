package com.multi.dianno.practice.prac1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BeanOne beanOne() {
        return new BeanOne();
    }

    @Bean
    public BeanTwo beanTwo(BeanOne beanOne) {
        return new BeanTwo(beanOne);
    }

}
