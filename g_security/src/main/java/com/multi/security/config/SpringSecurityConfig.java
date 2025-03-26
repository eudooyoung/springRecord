package com.multi.security.config;

import com.multi.security.authentication.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public SpringSecurityConfig(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    /*검색할 때 스프링부트 3버전에 세큐리티 6버전*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure() {
        return (web -> web.ignoring().requestMatchers("/static/**")); // static 경로 하위 파일은 제외 돼도록 설정
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        Map<String, List<String>> permitMap = customUserDetailService.getPermitListMap();
        List<String> adminList = permitMap.get("adminPermitList");
        List<String> memberList = permitMap.get("memberPermitList");

        http.csrf(AbstractHttpConfigurer::disable) //csrf 비활성화 설정
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        /*.requestMatchers("/menu/**").authenticated() // 인증된 사용자만 접근 가능하게 하겠다.
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("order/**").hasAnyRole("Admin", "USER")
                        .anyRequest().permitAll()*/

                        .requestMatchers(adminList.toArray(new String[adminList.size()])).hasRole("ADMIN")
                        .requestMatchers(memberList.toArray(new String[memberList.size()])).hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/member/login")
                        .defaultSuccessUrl("/", true)
                        .failureForwardUrl("/error/login"))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/")
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/error/denied"));
        return http.build();
    }

    /*http.csrf(AbstractHttpConfigurer::disable)
            .formLogin(form -> form
                    .loginPage(("/member/login"))
                    .defaultSuccessUrl("/",true)
                    .failureForwardUrl("/error/login"))
            .logout(logout-> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/")
            );
    return http.build();*/
}
