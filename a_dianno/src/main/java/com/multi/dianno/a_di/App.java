package com.multi.dianno.a_di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    /**IOC & DI :
     *  개발자가 직접 new 하여 객체를 생성하던 방식에서
     *  개발자의 부담을 덜어주며, 보다 편하게 개발 가능하도록 스프링 자체에서 객체를 생성, 수정, 삭제 (소멸 ) 하도록 구현한 기술


     1. 생성자를 통한 의존성 주입
     Person p = new Person("유재석", "건물주");

     2. setter 를 통한 의존성 주입
     p.setName("남주혁");

     3. 다른 메소드를 통한 의존성 주입
     Connection con = getConnection();

     어노테이션을 활용한 객체 의존성 주입
     어노테이션이 붙은 클래스를 탐색해서 컨테이너에 자동으로 등록(컴포넌트 스캔)

     --> @Component(클래스) , @Bean(메소드, @Configuration))


     ApplicationContext는 스프링의 핵심 인터페이스로, 설정 클래스에서 정의된 빈을 관리하고 제공
     AnnotationConfigApplicationContext는 자바 기반 설정을 사용하는 컨텍스트
     빈 사용: context.getBean(Class<T>) 메서드를 사용하여 스프링 컨테이너에서 빈을 가져와 메서드를 호출



     런타임 주입 과정
     스프링 컨테이너 초기화: 애플리케이션이 시작되면 스프링 컨테이너가 설정 파일(@Configuration 클래스나 applicationContext.xml 등)을 읽고 빈(Bean)을 생성
     의존성 주입: 빈 생성 이후 의존성 주입이 이루어지며, @Autowired, @Inject, 생성자 주입 등을 통해 필요한 의존성이 주입
     애플리케이션 실행: 의존성 주입이 완료된 상태에서 애플리케이션이 본격적으로 실행


     **/
    public static void main(String[] args) {

//        만들어지는 시점에서 등록되어 있는 생성자 호출
//        AppConfig 등록
//        앱컨피그 방식
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomBean customBean = context.getBean(CustomBean.class);

        customBean.sayHello();

        ConstructorBean constructorBean = context.getBean(ConstructorBean.class);

        constructorBean.doWork();

        SetterBean setterBean = context.getBean(SetterBean.class);

        setterBean.displayValue();

//        이름으로 가져올 때 (형변환 필요):
//        Object bean = context.getBean("customBean");
//        CustomBean customBean = (CustomBean) bean;  // 형변환 필요
//        customBean.sayHello();
//        타입으로 가져올 때 (형변환 불필요):
//        CustomBean customBean = context.getBean(CustomBean.class);  // 형변환 불필요
//        customBean.sayHello();
//        이름과 타입으로 가져올 때 (형변환 불필요):
//        CustomBean customBean = context.getBean("customBean", CustomBean.class);  // 형변환 불필요
//        customBean.sayHello();
    }
}
