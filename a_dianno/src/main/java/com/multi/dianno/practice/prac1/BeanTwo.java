package com.multi.dianno.practice.prac1;

public class BeanTwo {

    private BeanOne beanOne;

    public BeanTwo(BeanOne beanOne) {
        System.out.println(beanOne);
        this.beanOne = beanOne;
    }

    public void call() {
        beanOne.call();
    }
}
