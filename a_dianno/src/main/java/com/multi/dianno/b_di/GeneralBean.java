package com.multi.dianno.b_di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//의존성 주입 방식: 일반 메서드를 통해 여러 의존성을 한꺼번에 주입받는 방식.

//@Autowired를 사용해 여러 의존성을 동시에 주입 가능.

@Component
public class GeneralBean {
    private DependencyBean dependencyBean;
    private AnotherDependencyBean anotherDependencyBean;

    @Autowired
    public void init(DependencyBean dependencyBean, AnotherDependencyBean anotherDependencyBean) {
        this.dependencyBean = dependencyBean;
        this.anotherDependencyBean = anotherDependencyBean;
        System.out.println("init 호출됨");

    }

    public void displayValue() {
        System.out.println("GeneralBean dependencyBean: " + dependencyBean+ " GeneralBean anotherDependency: " + anotherDependencyBean);
        dependencyBean.showMessage();
    }
}
