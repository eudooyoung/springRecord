package com.multi.dianno.b_di;

import org.springframework.stereotype.Component;

@Component
public class AnotherDependencyBean {
    public void showMessage() {
        System.out.println("AnotherDependencyBean showMessage 호출 ");
    }
}
