package com.multi.dianno.b_di;

import org.springframework.stereotype.Component;

@Component
public class DependencyBean {
    public void showMessage() {
        System.out.println("DependencyBean showMessage 호출 ");
    }

//    순환참조
//    @Autowired
//    private FieldBean fieldBean;
//    필드 주입은 서로 참조하고 있는 상황인데, 에러가 발생하지 않음

//    private ConstructorBean constructorBean;
//    순환참조 안됨
//    @Autowired
//    public DependencyBean(ConstructorBean constructorBean){
//        this.constructorBean = constructorBean;
//    }

}
