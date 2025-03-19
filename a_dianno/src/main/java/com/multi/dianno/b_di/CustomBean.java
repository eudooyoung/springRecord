package com.multi.dianno.b_di;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

//POJO
//@Component 는 클래스 레벨에서 선언함으로써 스프링이 런타임시에 컴포넌트스캔을 하여 자동으로 빈을 찾고(detect) 등록하는 애노테이션
// bean의 Id는 lower Camel Case(앞자리 소문자)를 사용 <- () 에 별칭(이름) 작성하지 않았을 때
@Component
//@Scope("prototype") // 기본 객체 생성은 싱글톤으로, 프로토타입은 매번 새로운 객체로 생성
public class CustomBean {
    public void sayHello(){
        System.out.println("CustomBean의 sayHello 호출");
    }

    @PostConstruct
    public void init() {
        System.out.println("빈을 초기화 함");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("빈을 소멸 함");
    }
}
