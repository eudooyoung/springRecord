package com.multi.dianno.b_di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//POJO
//생성자 주입을 통해 DependencyBean 을 받아서 사용하는 클래스

//생성 시점에 단 한 번만 호출되어, 불변 및 필수적인 의존성에 적합.
//생성자가 하나일 경우 @Autowired 생략 가능.
//주입받는 필드를 final로 선언해 불변성을 보장하고, 의존성 누락 시 컴파일 오류로 확인 가능.

//장점:
//불변성: 객체 생성 후 의존성이 변경되지 않음.
//순환 참조 방지: 순환 참조 시 오류를 발생시켜 개발 단계에서 문제를 발견할 수 있음.

@Component
public class ConstructorBean {

//    final 필드를 사용해서 의존성을 불변으로 유지
    private final DependencyBean dependencyBean;

// 생성자 주입
// @Autowired는 의존성 주입에 사용 final 필드를 사용하여 의존성을 불변으로 유지
// @Autowired 없이 생성자 주입 가능 - 생성자가 하나뿐인 경우, 해당 생성자를 통해 자동으로 의존성을 주입하는 방식을 지원

    @Autowired
    public ConstructorBean(DependencyBean dependencyBean) {
        System.out.println("ConstructorBean 생성자에서 DependencyBean 주입 " + dependencyBean);
        this.dependencyBean = dependencyBean;
    }

    public void doWork() {
        System.out.println("ConstructorBean 에서 dependencyBean 메소드 호출 " + dependencyBean);
        dependencyBean.showMessage();
    }
}
