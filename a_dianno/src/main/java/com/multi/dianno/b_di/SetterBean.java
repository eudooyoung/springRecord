package com.multi.dianno.b_di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//POJO
//Setter 메소드를 통해 값을 주입 받는 클래스

//@Autowired를 사용해 의존성을 주입하며, 선택적 또는 변경 가능한 의존성에 적합.
//외부에서 의존성을 변경할 수 있기 때문에 가변성이 필요한 경우 사용.

@Component("setterBean2")
public class SetterBean {

    private DependencyBean dependencyBean;

    @Autowired
    public void setDependencyBean(DependencyBean dependencyBean) {
        System.out.println("SetterBean 생성자에서 DependencyBean 주입 " + dependencyBean);
        this.dependencyBean = dependencyBean;
    }

    public void displayValue() {
        System.out.println("SetterBean 에서 dependencyBean 메소드 호출 " + dependencyBean);
        dependencyBean.showMessage();
    }
}
