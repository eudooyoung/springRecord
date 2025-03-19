package com.multi.shop.common;

import com.multi.shop.common.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {

        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>(new LoginFilter());

        registrationBean.setOrder(1); // 필터 순서 지정
        registrationBean.addUrlPatterns("/product/*");

        return registrationBean;
    }
}
