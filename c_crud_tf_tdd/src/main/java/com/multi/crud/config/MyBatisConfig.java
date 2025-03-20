package com.multi.crud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.multi.crud.model.dao"}) // 패키지 내에 있는 mapper를 스캔하여 자동으로 등록하는 방식
public class MyBatisConfig {
}
