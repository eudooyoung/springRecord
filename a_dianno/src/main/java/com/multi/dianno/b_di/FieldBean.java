package com.multi.dianno.b_di;

//필드에 직접 @Autowired를 붙여 주입받는 방식.

//간결한 코드 작성이 가능하지만, 외부에서 접근이 불가능
//필드를 final로 선언할 수 없어 불변성을 보장하기 어려움.

//단점:
//DI 컨테이너 내에서만 작동하며 순수 자바 환경에서는 주입되지 않아 테스트가 어려움.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldBean {

    @Autowired
    private DependencyBean dependencyBean;

    public void displayValue() {
        System.out.println("FieldBean dependencyBean: " + dependencyBean);
        dependencyBean.showMessage();
    }

//    필드를 수정할 수 있는 메서드가 있으면 가변, 없으면 불면 상태
    public void setDependencyBean(DependencyBean dependencyBean) {
        this.dependencyBean = dependencyBean;
    }
}
