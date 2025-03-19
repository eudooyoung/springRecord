package com.multi.dianno.a_di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    /**@Configuration: 스프링에서 사용되는 설정 클래스임을 명시하는 어노테이션, 스프링 컨테이너에 의해 관리
     @Bean: 스프링 컨테이너에 의해 관리될 객체(빈)를 정의하는 메서드에 사용하는 어노테이션
     @Bean이 선언된 메서드는 스프링 컨테이너에 의해 호출되고, 그 결과로 생성된 객체가 빈으로 등록됨

     POJO와 스프링 빈의 개념
     POJO: 특정 프레임워크에 의존하지 않는 순수한 자바 객체. 여기서는 CustomBean, DependencyBean, ConstructorBean, SetterBean
     스프링 빈: 스프링이 관리하는 객체로, @Configuration 클래스에서 @Bean 메서드를 사용하여 등록, 스프링 컨테이너가 이러한 빈을 관리하고, 의존성을 주입함

     **/

    @Bean
    public CustomBean customBean() {
//        매개 변수가 없는 생성자
        return new CustomBean();
    }

    @Bean
    public DependencyBean dependencyBean() {
        return new DependencyBean();
    }

//    주입:
//    생성자가 만들어지면서 먼저 실행됨
    @Bean
    public ConstructorBean constructorBean() {
//        매개변수가 있는 생성자 실행
//        위에서 만들어진 dependencyBean 메소드 호출
        return new ConstructorBean(dependencyBean());
    }

    @Bean // 메소드에서만 사용가능한 어노테이션
    public SetterBean setterBean() {
        SetterBean setterBean = new SetterBean();
        setterBean.setDependencyBean(dependencyBean());
        return setterBean;
    }
}
