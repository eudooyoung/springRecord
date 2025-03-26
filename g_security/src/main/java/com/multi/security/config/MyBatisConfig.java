package com.multi.security.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.multi.security"})
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessoinFactory(DataSource datasource) throws Exception {
        SqlSessionFactoryBean seb = new SqlSessionFactoryBean();
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml");

        seb.setMapperLocations(res);

        seb.setDataSource(datasource);
        // DTO 패키지에 있는 클래스들을 자동으로 별칭 등록 (MemberDto -> memberDto)
        seb.setTypeAliasesPackage("com.multi.security.member.dto");

        SqlSessionFactory sqlSessionFactory = seb.getObject();
        if (sqlSessionFactory != null) {
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        }

        return seb.getObject();
    }
}
