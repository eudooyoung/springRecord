package com.multi.dianno.b_di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan을 통해 패키지를 스캔하도록 설정 하여 해당 패키지내의 @Component 를 빈으로 등록
// 패키지에 있는 클래스를 읽어서 Bean 에 등록하는 어노테이션
@ComponentScan(basePackages = "com.multi.dianno.b_di")
public class App {
    public static void main(String[] args) {

//        어노테이션 컨피그 방식
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        CustomBean customBean = context.getBean(CustomBean.class);

        customBean.sayHello();

        ConstructorBean constructorBean = context.getBean(ConstructorBean.class);

        constructorBean.doWork();

//        이름 지정해서
        SetterBean setterBean = context.getBean("setterBean2", SetterBean.class);

        setterBean.displayValue();

        GeneralBean generalBean = context.getBean(GeneralBean.class);

        generalBean.displayValue();

        FieldBean fieldBean = context.getBean(FieldBean.class);

        fieldBean.displayValue();


//        생성자 주입이 권장되는 이유
//        불변성 보장:
//             생성자 주입은 생성 시점 이후 의존성이 변경되지 않도록 final로 선언할 수 있어 코드의 안정성이 높음.
//             컴파일 오류를 통해 의존성 누락 확인: 의존성 누락 시 실행 이전에 오류를 확인할 수 있음.
//             순환 참조 방지: 순환 참조가 발생할 경우 실행 시점에 오류가 발생해 개발 초기 단계에서 문제를 해결할 수 있음.

//        필드 주입을 사용하면 일시적으로 에러를 피할 수 있지만, 잠재적인 오류를 남기게 되므로 가능하면 생성자 주입을 통한 설계 변경이 바람직 함.
//

        CustomBean customBean2 = context.getBean(CustomBean.class);

        customBean2.sayHello();

        System.out.println(customBean == customBean2);

        System.out.println(context.isActive());

        context.close();

//        customBean.destroy();
//        customBean2.destroy();

        System.out.println(context.isActive());
    }
}
