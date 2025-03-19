package com.multi.dianno.a_di;

//POJO
//생성자 주입을 통해 DependencyBean 을 받아서 사용하는 클래스
public class ConstructorBean {

    private DependencyBean dependencyBean;

//    주입과정이, 생성자가 만들어지면서 먼저 일어남
    public ConstructorBean(DependencyBean dependencyBean) {
        System.out.println("생성자에서 DependencyBean이 주입되어 ConstructorBean 생성 " + dependencyBean);
        this.dependencyBean = dependencyBean;
    }

    public void doWork() {
        System.out.println("ConstructorBean 에서 dependencyBean 메소드 호출 " + dependencyBean);
        dependencyBean.showMessage();
    }
}
