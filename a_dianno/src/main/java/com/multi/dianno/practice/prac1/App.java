package com.multi.dianno.practice.prac1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        BeanTwo beanTwo = new BeanTwo();
        BeanTwo beanTwo = context.getBean(BeanTwo.class);

        beanTwo.call();

    }
}
