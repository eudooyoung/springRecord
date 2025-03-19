package com.multi.dianno.a_di;

//POJO
//Setter 메소드를 통해 값을 주입 받는 클래스
public class SetterBean {

    private DependencyBean dependencyBean;

    public void setDependencyBean(DependencyBean dependencyBean) {
        System.out.println("setter 메소드에서 DependencyBean 주입 " + dependencyBean);
        this.dependencyBean = dependencyBean;
    }

    public void displayValue() {
        System.out.println("SetterBean 에서 dependencyBean 메소드 호출 " + dependencyBean);
        dependencyBean.showMessage();
    }
}
